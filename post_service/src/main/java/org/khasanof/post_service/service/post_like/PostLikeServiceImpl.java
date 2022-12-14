package org.khasanof.post_service.service.post_like;

import org.khasanof.post_service.criteria.post_like.PostLikeCriteria;
import org.khasanof.post_service.dto.post_like.*;
import org.khasanof.post_service.entity.like.LikeEntity;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.entity.post_like.PostLikeEntity;
import org.khasanof.post_service.enums.like.LikeTypeEnum;
import org.khasanof.post_service.enums.rating.RatingPointEnum;
import org.khasanof.post_service.exceptions.exceptions.AlreadyCreatedException;
import org.khasanof.post_service.mapper.post_like.PostLikeMapper;
import org.khasanof.post_service.repository.post_like.PostLikeRepository;
import org.khasanof.post_service.service.AbstractService;
import org.khasanof.post_service.service.post.PostService;
import org.khasanof.post_service.service.post_rating.PostRatingService;
import org.khasanof.post_service.utils.BaseUtils;
import org.khasanof.post_service.validator.post_like.PostLikeValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostLikeServiceImpl extends AbstractService<PostLikeRepository, PostLikeMapper, PostLikeValidator> implements PostLikeService {

    private final PostService postService;
    private final PostRatingService postRatingService;
    private final MongoTemplate mongoTemplate;

    public PostLikeServiceImpl(PostLikeRepository repository, PostLikeMapper mapper, PostLikeValidator validator, PostService postService, PostRatingService postRatingService, MongoTemplate mongoTemplate) {
        super(repository, mapper, validator);
        this.postService = postService;
        this.postRatingService = postRatingService;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void create(PostEntity entity) {
        PostLikeEntity like = mongoTemplate.findOne(
                Query.query(new Criteria("postId")
                        .is(entity)), PostLikeEntity.class);
        if (Objects.isNull(like)) {
            var likeEntity = new PostLikeEntity(entity, new LinkedList<>());
            repository.save(likeEntity);
        } else {
            throw new AlreadyCreatedException("Already created Post Like!");
        }
    }

    @Override
    public void addLike(PostLikeCreateDTO dto) {
        validator.validCreateDTO(dto);
        PostLikeEntity postLike = mongoTemplate.findOne(
                Query.query(new Criteria("postId")
                        .is(postService.getEntity(dto.getLikePostId()))),
                PostLikeEntity.class);
        if (Objects.isNull(postLike)) {
            addIsNull(dto);
        } else {
            addNonNull(dto, postLike);
        }
        BaseUtils.EXECUTOR_SERVICE
                .execute(() -> postRatingService.updateRatingCount(dto.getLikePostId(),
                        RatingPointEnum.LIKE, false));
    }

    private void addNonNull(PostLikeCreateDTO dto, PostLikeEntity postLike) {
        LinkedList<LikeEntity> likes = postLike.getLikes();
        LikeEntity entity = likes.stream()
                .filter(a -> a.getUserId()
                        .equals(dto.getUserId()))
                .findFirst().orElse(null);
        if (Objects.isNull(entity)) {
            likes.add(new LikeEntity(dto.getUserId(), dto.getType()));
            postLike.setLikes(likes);
            postLike.setUpdatedAt(Instant.now());
            postLike.setUpdatedBy(dto.getUserId());
            repository.save(postLike);
        } else {
            if (!entity.getType().equalsIgnoreCase(dto.getType())) {
                likes.remove(entity);
                entity.setType(dto.getType());
                likes.add(entity);
                postLike.setLikes(likes);
                postLike.setUpdatedAt(Instant.now());
                postLike.setUpdatedBy(dto.getUserId());
                repository.save(postLike);
            }
        }
    }

    private void addIsNull(PostLikeCreateDTO dto) {
        if (postService.existByIdAndCheckBlocked(dto.getLikePostId())) {
            throw new NotFoundException("Post is blocked!");
        }
        PostLikeEntity postLikeEntity = mapper.toCreateDTO(dto);
        LinkedList<LikeEntity> likes = new LinkedList<>();
        likes.add(new LikeEntity(dto.getUserId(), dto.getType()));
        postLikeEntity.setPostId(postService.getEntity(dto.getLikePostId()));
        postLikeEntity.setLikes(likes);
        repository.save(postLikeEntity);
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id)) {
            throw new NotFoundException("Post Like not found");
        }
        repository.deleteById(id);
    }

    @Override
    public void deleteLike(PostLikeDeleteDTO dto) {
        validator.validDeleteDTO(dto);
        if (!postService.existById(dto.getLikePostId())) {
            throw new NotFoundException("Post not found");
        }
        Optional<PostLikeEntity> optional = repository.findByPostIdQuery(dto.getLikePostId());
        if (optional.isEmpty()) {
            throw new NotFoundException("Post Like not found");
        }
        PostLikeEntity likeEntity = optional.get();
        LinkedList<LikeEntity> likes = likeEntity.getLikes();
        if (!likes.removeIf(l -> l.getUserId().equals(dto.getUserId()))) {
            throw new NotFoundException("Like not found with UserId -> " + dto.getUserId());
        }
        likeEntity.setLikes(likes);
        repository.save(likeEntity);
        BaseUtils.EXECUTOR_SERVICE
                .execute(() -> postRatingService.updateRatingCount(dto.getLikePostId(),
                        RatingPointEnum.LIKE, true));
    }

    @Override
    public PostLikeGetDTO get(String id) {
        validator.validKey(id);
        return returnToGetDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post Like not found");
                        })
        );
    }

    @Override
    public PostLikeDetailDTO detail(String id) {
        validator.validKey(id);
        return mapper.fromDetailDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post Like not found");
                        })
        );
    }

    @Override
    public List<PostLikeGetDTO> list(PostLikeCriteria criteria) {
        return repository.findAll(
                        PageRequest.of(
                                criteria.getPage(), criteria.getSize()
                        )).stream()
                .map(this::returnToGetDTO)
                .toList();
    }

    @Override
    public long count(String postId) {
        validator.validKey(postId);
        return repository.findById(postId)
                .orElseThrow(() -> {
                    throw new NotFoundException("Post Like not found");
                }).getLikes()
                .size();
    }

    @Override
    public PostLikeTypeCount getByPostIdAndTypeLikeCount(String postId, String type) {
        validator.validLikeTypeCount(postId, type);
        PostLikeEntity postLike = mongoTemplate.findOne(
                Query.query(new Criteria("postId")
                        .is(postService.getEntity(postId))),
                PostLikeEntity.class);
        if (Objects.isNull(postLike)) {
            throw new NotFoundException("Post Like not found");
        }
        long count = postLike.getLikes()
                .stream()
                .filter(f -> f.getType().equalsIgnoreCase(type))
                .count();
        String var = LikeTypeEnum.getType(type);
        if (Objects.isNull(var)) {
            throw new NotFoundException("Like type not found");
        }
        return new PostLikeTypeCount(postId, (int) count, var);
    }

    @Override
    public PostLikeGetDTO getByPostId(String id) {
        validator.validKey(id);
        return returnToGetDTO(
                repository.findByPostIdQuery(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post Like not found");
                        })
        );
    }

    private PostLikeGetDTO returnToGetDTO(PostLikeEntity entity) {
        PostLikeGetDTO postLikeGetDTO = mapper.fromGetDTO(entity);
        postLikeGetDTO.setLikePostId(entity.getPostId().getId());
        postLikeGetDTO.setLikesCount(entity.getLikes().size());
        return postLikeGetDTO;
    }
}
