package org.khasanof.post_service.service.post;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.khasanof.post_service.criteria.post.PostCatCriteria;
import org.khasanof.post_service.criteria.post.PostCriteria;
import org.khasanof.post_service.criteria.post.PostRatingCriteria;
import org.khasanof.post_service.criteria.post.PostUseCriteria;
import org.khasanof.post_service.dto.auth_following.AuthFollowingGetDTO;
import org.khasanof.post_service.dto.auth_user.AuthUserGetDTO;
import org.khasanof.post_service.dto.post.*;
import org.khasanof.post_service.dto.post_category.PostCategoryAddAllDTO;
import org.khasanof.post_service.dto.post_comment.PostCommentDetailDTO;
import org.khasanof.post_service.dto.post_rating.PostRatingCreateDTO;
import org.khasanof.post_service.dto.post_rating.PostRatingGetDTO;
import org.khasanof.post_service.entity.auth_user.AuthUserEntity;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.entity.post_category.PostCategoryEntity;
import org.khasanof.post_service.entity.post_rating.PostRatingEntity;
import org.khasanof.post_service.enums.post.PostStatusEnum;
import org.khasanof.post_service.enums.rating.RatingTypeEnum;
import org.khasanof.post_service.mapper.post.PostMapper;
import org.khasanof.post_service.mapper.post_rating.PostRatingMapper;
import org.khasanof.post_service.repository.post.PostRepository;
import org.khasanof.post_service.response.Data;
import org.khasanof.post_service.service.AbstractService;
import org.khasanof.post_service.service.category.CategoryService;
import org.khasanof.post_service.service.post_category.PostCategoryService;
import org.khasanof.post_service.service.post_comment.PostCommentService;
import org.khasanof.post_service.service.post_like.PostLikeService;
import org.khasanof.post_service.service.post_rating.PostRatingService;
import org.khasanof.post_service.service.post_save.PostSaveService;
import org.khasanof.post_service.service.post_view.PostViewService;
import org.khasanof.post_service.utils.BaseUtils;
import org.khasanof.post_service.validator.post.PostValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Service
public class PostServiceImpl extends AbstractService<PostRepository, PostMapper, PostValidator> implements PostService {

    private final PostViewService viewService;
    private final PostSaveService saveService;
    private final PostLikeService likeService;
    private final PostCommentService commentService;
    private final PostCategoryService postCategoryService;
    private final CategoryService categoryService;
    private final PostRatingService postRatingService;
    private final PostRatingMapper postRatingMapper;
    private final MongoTemplate mongoTemplate;

    public PostServiceImpl(PostRepository repository, PostMapper mapper, PostValidator validator, @Lazy PostViewService viewService, @Lazy PostSaveService saveService, @Lazy PostLikeService likeService, @Lazy PostCommentService commentService, @Lazy PostCategoryService postCategoryService, CategoryService categoryService, @Lazy PostRatingService postRatingService, PostRatingMapper postRatingMapper, MongoTemplate mongoTemplate) {
        super(repository, mapper, validator);
        this.viewService = viewService;
        this.saveService = saveService;
        this.likeService = likeService;
        this.commentService = commentService;
        this.postCategoryService = postCategoryService;
        this.categoryService = categoryService;
        this.postRatingService = postRatingService;
        this.postRatingMapper = postRatingMapper;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void create(PostCreateDTO dto) {
        validator.validCreateDTO(dto);
        dto.setVisibility(dto.getVisibility().toUpperCase(Locale.ROOT));
        PostEntity postEntity = mapper.toCreateDTO(dto);
        AuthUserEntity userEntity = new AuthUserEntity();
        BeanUtils.copyProperties(getAuthUserDTO(dto.getPostUserId()), userEntity);
        postEntity.setUserId(userEntity);
        postEntity.setCreatedBy(userEntity.getId());
        postEntity.setStatus(PostStatusEnum.ACTIVE.getValue());
        PostEntity entity = repository.save(postEntity);
        postCategoryService.addAllCategory(new PostCategoryAddAllDTO(entity.getId(), dto.getCategoriesIds()));
        postRatingService.create(new PostRatingCreateDTO(entity.getId()));
    }

    @Override
    public void update(PostUpdateDTO dto) {
        validator.validUpdateDTO(dto);
        PostEntity post = repository.findById(dto.getId())
                .orElseThrow(() -> {
                    throw new NotFoundException("Post not found");
                });
        BeanUtils.copyProperties(dto, post, "id");
        repository.save(post);
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id)) {
            throw new NotFoundException("Post not found");
        }
        repository.deleteById(id);
    }

    @Override
    public PostGetDTO get(String id) {
        validator.validKey(id);
        return entityParseDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post not found");
                        }));
    }

    @Override
    public PostDetailDTO detail(String id) {
        validator.validKey(id);
        PostDetailDTO detailDTO = mapper.fromDetailDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post not found");
                        }));
        detailDTO.setLikesCount(likeService.getByPostId(id).getLikesCount());
        detailDTO.setViewsCount(viewService.getByPostId(id).getViewsCount());
        detailDTO.setCommentsCount(commentService.getByPostId(id).getCommentsCount());
        detailDTO.setSavesCount(saveService.getByPostId(id).getSaveCount());
        return detailDTO;
    }

    @Override
    public List<PostGetDTO> list(PostCriteria criteria) {
        return repository.findAll(
                        PageRequest.of(
                                criteria.getPage(),
                                criteria.getSize(),
                                criteria.getDirection(),
                                criteria.getFieldsEnum().getValue()
                        )).stream()
                .map(this::entityParseDTO)
                .toList();
    }

    @Override
    public List<PostGetDTO> getAllWithCreatedBy(String userId) {
        return repository.findAllByCreatedBy(userId)
                .stream()
                .map(this::entityParseDTO)
                .toList();
    }

    @Override
    public List<PostGetDTO> listWithCategory(PostCatCriteria catCriteria) {
        List<PostCategoryEntity> categories = mongoTemplate.find(
                Query.query(new Criteria("categories")
                                .in(categoryService.getEntity(catCriteria.getCategoryId())))
                        .with(PageRequest.of(catCriteria.getPage(), catCriteria.getSize(),
                                catCriteria.getDirection(), catCriteria.getFieldsEnum().getValue())),
                PostCategoryEntity.class);
        return categories
                .stream()
                .map(PostCategoryEntity::getPostId)
                .map(this::entityParseDTO)
                .toList();
    }

    @Override
    public List<PostGetDTO> listWithUserId(PostUseCriteria criteria) {
        AuthFollowingGetDTO authFollowingGetDTO = authFollowingGetDTO(criteria.getUserId());
        System.out.println("authFollowingGetDTO = " + authFollowingGetDTO);
        if (authFollowingGetDTO.getFollowingsId().size() == 0) {
            List<PostRatingEntity> entities = mongoTemplate.find(
                    Query.query(new Criteria("ratingType")
                                    .is(RatingTypeEnum.RECOMMENDED.getValue())
                                    .orOperator(new Criteria("ratingType")
                                            .is(RatingTypeEnum.POPULAR.getValue())))
                            .with(PageRequest.of(criteria.getPage(), criteria.getSize(), criteria.getDirection(),
                                    criteria.getFieldsEnum().getValue())),
                    PostRatingEntity.class);
            return entities.stream()
                    .map(PostRatingEntity::getPostId)
                    .map(this::entityParseDTO)
                    .toList();
        }
        List<String> ids = authFollowingGetDTO.getFollowingsId();
        List<PostEntity> entityList = ids.stream()
                .map(repository::findAllByCreatedByOrderByCreatedAtAsc)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(PostEntity::getCreatedAt).reversed())
                .toList();
        entityList.forEach(System.out::println);
        if (entityList.size() > criteria.getSize()) {

        }
        return null;
    }

    @Override
    public List<PostGetDTO> listWithRating(PostRatingCriteria ratingCriteria) {
        // TODO writing logic
        List<PostRatingEntity> list = mongoTemplate.find(
                Query.query(new Criteria("ratingType")
                                .is(ratingCriteria.getTypeEnum().getValue()))
                        .with(PageRequest.of(ratingCriteria.getPage(), ratingCriteria.getSize(),
                                ratingCriteria.getDirection(), ratingCriteria.getFieldsEnum().getValue())),
                PostRatingEntity.class);
        return list
                .stream()
                .map(PostRatingEntity::getPostId)
                .map(this::entityParseDTO)
                .toList();
    }

    @Override
    public PostDetWComDTO getByPostIdDetailAndComments(String id, Integer count) {
        validator.validKey(id);
        PostDetWComDTO detWComDTO = mapper.fromPostDetWComDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post not found");
                        }));
        detWComDTO.setLikesCount(likeService.getByPostId(id).getLikesCount());
        detWComDTO.setViewsCount(viewService.getByPostId(id).getViewsCount());
        detWComDTO.setAllCommentsCount(commentService.getByPostId(id).getCommentsCount());
        detWComDTO.setSavesCount(saveService.getByPostId(id).getSaveCount());
        PostCommentDetailDTO postCommentDetailDTO = commentService.detailCommentsCount(id, count);
        detWComDTO.setComments(postCommentDetailDTO.getComments());
        detWComDTO.setCommentsCount(postCommentDetailDTO.getCommentsCount());
        return detWComDTO;
    }

    @Override
    public PostEntity getEntity(String id) {
        validator.validKey(id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Post not found");
                });
    }

    @Override
    public boolean existById(String postId) {
        validator.validKey(postId);
        return repository.existsById(postId);
    }

    @Override
    public boolean existByIdAndCheckBlocked(String postId) {
        return PostStatusEnum.BLOCKED.getValue().equals(
                repository.findById(postId)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post not found");
                        })
                        .getStatus());
    }

    private PostGetDTO entityParseDTO(PostEntity entity) {
        PostGetDTO dto = mapper.fromGetDTO(entity);
        dto.setPostUserId(entity.getUserId().getId());
        return dto;
    }

    private PostRatingGetDTO returnToGetDTO(PostRatingEntity entity) {
        PostRatingGetDTO postRatingGetDTO = postRatingMapper.fromGetDTO(entity);
        postRatingGetDTO.setRatingPostId(entity.getPostId().getId());
        postRatingGetDTO.setRatingType(entity.getRatingType());
        return postRatingGetDTO;
    }

    private AuthUserGetDTO getAuthUserDTO(String id) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            ObjectMapper objectMapper = new ObjectMapper();
            HttpGet httpGet = new HttpGet(BaseUtils.AUTH_SERVICE + "/auth_user/get/" + id);
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != 200)
                throw new NotFoundException("User not found");
            return objectMapper.readValue(httpResponse.getEntity().getContent(), new TypeReference<Data<AuthUserGetDTO>>() {
            }).getData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private AuthFollowingGetDTO authFollowingGetDTO(String userId) {
        return BaseUtils.authFollowingGetCallAPI(userId, "User Following ont found");
    }

    // TODO post lani ob keladigan api kere obuna bogan bunachilari boyicha bolmasa top reyitingdagi postlar
}
