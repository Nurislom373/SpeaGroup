package org.khasanof.post_service.service.post_view;

import org.khasanof.post_service.criteria.post_view.PostViewCriteria;
import org.khasanof.post_service.dto.post_view.PostViewCreateDTO;
import org.khasanof.post_service.dto.post_view.PostViewDetailDTO;
import org.khasanof.post_service.dto.post_view.PostViewGetDTO;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.entity.post_view.PostViewEntity;
import org.khasanof.post_service.entity.view.ViewEntity;
import org.khasanof.post_service.enums.rating.RatingPointEnum;
import org.khasanof.post_service.mapper.post_view.PostViewMapper;
import org.khasanof.post_service.repository.post_view.PostViewRepository;
import org.khasanof.post_service.service.AbstractService;
import org.khasanof.post_service.service.post.PostService;
import org.khasanof.post_service.service.post_rating.PostRatingService;
import org.khasanof.post_service.utils.BaseUtils;
import org.khasanof.post_service.validator.post_view.PostViewValidator;
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

@Service
public class PostViewServiceImpl extends AbstractService<PostViewRepository, PostViewMapper, PostViewValidator> implements PostViewService {

    private final PostService postService;
    private final PostRatingService postRatingService;
    private final MongoTemplate mongoTemplate;


    public PostViewServiceImpl(PostViewRepository repository, PostViewMapper mapper, PostViewValidator validator, PostService postService, PostRatingService postRatingService, MongoTemplate mongoTemplate) {
        super(repository, mapper, validator);
        this.postService = postService;
        this.postRatingService = postRatingService;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void create(PostEntity entity) {
        PostViewEntity viewEntity = mongoTemplate.findOne(
                Query.query(new Criteria("postId")
                        .is(entity)), PostViewEntity.class);
        if (Objects.isNull(viewEntity)) {
            PostViewEntity view = new PostViewEntity(entity, new LinkedList<>());
            repository.save(view);
        } else {
            throw new RuntimeException("Already Created Post View!");
        }
    }

    @Override
    public void addView(PostViewCreateDTO dto) {
        validator.validCreateDTO(dto);
        PostEntity entity = postService.getEntity(dto.getViewPostId());
        PostViewEntity viewEntity = mongoTemplate.findOne(
                Query.query(new Criteria("postId")
                        .is(entity)), PostViewEntity.class);
        if (Objects.nonNull(viewEntity)) {
            LinkedList<ViewEntity> views = viewEntity.getViews();
            boolean anyMatch = views.stream()
                    .anyMatch(any -> any.getUserId().equals(dto.getUserId()));
            if (!anyMatch) {
                views.add(new ViewEntity(dto.getUserId()));
                viewEntity.setViews(views);
                repository.save(viewEntity);
                BaseUtils.EXECUTOR_SERVICE.execute(() ->
                        postRatingService.updateRatingCount(dto.getViewPostId(),
                                RatingPointEnum.VIEW, false));
            }
        } else {
            throw new RuntimeException("PostView not found");
        }
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id)) {
            throw new NotFoundException("Post View not found");
        }
        repository.deleteById(id);
    }

    @Override
    public PostViewGetDTO getByPostId(String id) {
        validator.validKey(id);
        return returnToGetDTO(
                repository.findByPostIdQuery(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post View not found");
                        })
        );
    }

    @Override
    public PostViewGetDTO get(String id) {
        validator.validKey(id);
        return returnToGetDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post View not found");
                        })
        );
    }

    @Override
    public PostViewDetailDTO detail(String id) {
        validator.validKey(id);
        return mapper.fromDetailDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post View not found");
                        })
        );
    }

    @Override
    public List<PostViewGetDTO> list(PostViewCriteria criteria) {
        return repository.findAll(
                        PageRequest.of(criteria.getPage(), criteria.getSize())
                )
                .stream()
                .map(this::returnToGetDTO)
                .toList();
    }

    private PostViewGetDTO returnToGetDTO(PostViewEntity entity) {
        PostViewGetDTO postViewGetDTO = mapper.fromGetDTO(entity);
        postViewGetDTO.setViewPostId(entity.getPostId().getId());
        postViewGetDTO.setViewsCount(entity.getViews().size());
        return postViewGetDTO;
    }
}
