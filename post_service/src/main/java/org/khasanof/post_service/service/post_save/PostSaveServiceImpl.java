package org.khasanof.post_service.service.post_save;

import org.khasanof.post_service.criteria.post_save.PostSaveCriteria;
import org.khasanof.post_service.dto.post_save.PostSaveCreateDTO;
import org.khasanof.post_service.dto.post_save.PostSaveDeleteDTO;
import org.khasanof.post_service.dto.post_save.PostSaveDetailDTO;
import org.khasanof.post_service.dto.post_save.PostSaveGetDTO;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.entity.post_like.PostLikeEntity;
import org.khasanof.post_service.entity.post_save.PostSaveEntity;
import org.khasanof.post_service.entity.save.SaveEntity;
import org.khasanof.post_service.enums.rating.RatingPointEnum;
import org.khasanof.post_service.exceptions.exceptions.AlreadyCreatedException;
import org.khasanof.post_service.mapper.post.PostMapper;
import org.khasanof.post_service.mapper.post_save.PostSaveMapper;
import org.khasanof.post_service.repository.post_save.PostSaveRepository;
import org.khasanof.post_service.service.AbstractService;
import org.khasanof.post_service.service.post.PostService;
import org.khasanof.post_service.service.post_rating.PostRatingService;
import org.khasanof.post_service.utils.BaseUtils;
import org.khasanof.post_service.validator.post_save.PostSaveValidator;
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
public class PostSaveServiceImpl extends AbstractService<PostSaveRepository, PostSaveMapper, PostSaveValidator> implements PostSaveService {

    private final PostService postService;
    private final PostMapper postMapper;
    private final PostRatingService postRatingService;
    private final MongoTemplate mongoTemplate;

    public PostSaveServiceImpl(PostSaveRepository repository, PostSaveMapper mapper, PostSaveValidator validator, PostService postService, PostMapper postMapper, PostRatingService postRatingService, MongoTemplate mongoTemplate) {
        super(repository, mapper, validator);
        this.postService = postService;
        this.postMapper = postMapper;
        this.postRatingService = postRatingService;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void create(PostEntity entity) {
        PostSaveEntity save = mongoTemplate.findOne(
                Query.query(new Criteria("postId")
                .is(entity)), PostSaveEntity.class);
        if (Objects.isNull(save)) {
            var saveEntity = new PostSaveEntity(entity, new LinkedList<>());
            repository.save(saveEntity);
        } else {
            throw new AlreadyCreatedException("Already created Post Save!");
        }
    }

    @Override
    public void addSave(PostSaveCreateDTO dto) {
        validator.validCreateDTO(dto);
        PostSaveEntity saveEntity = mongoTemplate.findOne(
                Query.query(new Criteria("postId")
                        .is(postService.getEntity(dto.getSavePostId()))),
                PostSaveEntity.class);
        if (Objects.nonNull(saveEntity)) {
            LinkedList<SaveEntity> saves = saveEntity.getSaves();
            boolean anyMatch = saves.stream()
                    .anyMatch(any -> any.getUserId().equals(dto.getUserId()));
            if (anyMatch) {
                throw new RuntimeException("User already saved this post!");
            }
            saves.add(new SaveEntity(dto.getUserId()));
            saveEntity.setSaves(saves);
            saveEntity.setUpdatedAt(Instant.now());
            saveEntity.setCreatedBy("-1");
            repository.save(saveEntity);
        } else {
            if (postService.existByIdAndCheckBlocked(dto.getSavePostId())) {
                throw new RuntimeException("This Post is block");
            }
            PostEntity postEntity = postMapper.toGetDTO(postService.get(dto.getSavePostId()));
            PostSaveEntity postSaveEntity = mapper.toCreateDTO(dto);
            postSaveEntity.setPostId(postEntity);
            LinkedList<SaveEntity> saves = new LinkedList<>();
            saves.add(new SaveEntity(dto.getUserId()));
            postSaveEntity.setSaves(saves);
            repository.save(postSaveEntity);
        }
        BaseUtils.EXECUTOR_SERVICE.execute(() ->
                postRatingService.updateRatingCount(dto.getSavePostId(),
                        RatingPointEnum.SAVE, false));
    }

    @Override
    public void deleteSaved(PostSaveDeleteDTO dto) {
        validator.validDeleteDTO(dto);
        PostSaveEntity postSave = repository.findByPostIdQuery(dto.getSavePostId()).orElseThrow(() -> {
            throw new NotFoundException("Post Save not found");
        });
        LinkedList<SaveEntity> saves = postSave.getSaves();
        if (!saves.removeIf(f -> f.getUserId().equals(dto.getUserId()))) {
            throw new NotFoundException("User saved not found");
        }
        postSave.setSaves(saves);
        repository.save(postSave);
        postRatingService.updateRatingCount(dto.getSavePostId(), RatingPointEnum.SAVE, true);
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id)) {
            throw new NotFoundException("Post Save not found");
        }
        repository.deleteById(id);
    }

    @Override
    public PostSaveGetDTO getByPostId(String id) {
        validator.validKey(id);
        return returnToGetDTO(
                repository.findByPostIdQuery(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post Save not found");
                        })
        );
    }

    @Override
    public PostSaveGetDTO get(String id) {
        validator.validKey(id);
        return returnToGetDTO(
                repository.findById(id).orElseThrow(() -> {
                    throw new NotFoundException("Post Save not found");
                })
        );
    }

    @Override
    public PostSaveDetailDTO detail(String id) {
        validator.validKey(id);
        return mapper.fromDetailDTO(
                repository.findById(id).orElseThrow(() -> {
                    throw new NotFoundException("Post Save not found");
                })
        );
    }

    @Override
    public List<PostSaveGetDTO> list(PostSaveCriteria criteria) {
        return repository.findAll(
                        PageRequest.of(criteria.getPage(), criteria.getSize())
                ).stream()
                .map(this::returnToGetDTO)
                .toList();
    }

    private PostSaveGetDTO returnToGetDTO(PostSaveEntity entity) {
        PostSaveGetDTO postSaveGetDTO = mapper.fromGetDTO(entity);
        postSaveGetDTO.setSavePostId(entity.getPostId().getId());
        postSaveGetDTO.setSaveCount(entity.getSaves().size());
        return postSaveGetDTO;
    }
}
