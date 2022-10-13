package org.khasanof.post_service.service.post_rating;

import org.khasanof.post_service.criteria.post_rating.PostRatingCriteria;
import org.khasanof.post_service.dto.post_rating.PostRatingCreateDTO;
import org.khasanof.post_service.dto.post_rating.PostRatingDetailDTO;
import org.khasanof.post_service.dto.post_rating.PostRatingGetDTO;
import org.khasanof.post_service.entity.category.CategoryEntity;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.entity.post_category.PostCategoryEntity;
import org.khasanof.post_service.entity.post_rating.PostRatingEntity;
import org.khasanof.post_service.enums.rating.RatingPointEnum;
import org.khasanof.post_service.enums.rating.RatingTypeEnum;
import org.khasanof.post_service.mapper.post.PostMapper;
import org.khasanof.post_service.mapper.post_rating.PostRatingMapper;
import org.khasanof.post_service.repository.post_rating.PostRatingRepository;
import org.khasanof.post_service.service.AbstractService;
import org.khasanof.post_service.service.category.CategoryService;
import org.khasanof.post_service.service.category.CategoryServiceImpl;
import org.khasanof.post_service.service.post.PostService;
import org.khasanof.post_service.validator.post_rating.PostRatingValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class PostRatingServiceImpl extends AbstractService<PostRatingRepository, PostRatingMapper, PostRatingValidator> implements PostRatingService {

    private final PostService postService;
    private final PostMapper postMapper;

    public PostRatingServiceImpl(PostRatingRepository repository, PostRatingMapper mapper, PostRatingValidator validator, PostService postService, PostMapper postMapper) {
        super(repository, mapper, validator);
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @Override
    public void create(PostRatingCreateDTO dto) {
        validator.validCreateDTO(dto);
        if (repository.existsById(dto.getRatingPostId())) {
            throw new RuntimeException("Post Rating Already Created with Id");
        }
        PostRatingEntity postRatingEntity = mapper.toCreateDTO(dto);
        PostEntity postEntity = postMapper.toGetDTO(postService.get(dto.getRatingPostId()));
        if (Objects.isNull(postEntity)) {
            throw new NotFoundException("Post not found");
        }
        postRatingEntity.setPostId(postEntity);
        postRatingEntity.setLikesCount(0);
        postRatingEntity.setViewsCount(0);
        postRatingEntity.setSharesCount(0);
        postRatingEntity.setSavesCount(0);
        postRatingEntity.setRatingType(RatingTypeEnum.NEW.getValue());
        repository.save(postRatingEntity);
    }

    @Override
    public void setRating(PostRatingEntity entity) {
        Instant tomorrow = entity.getCreatedAt().plusNanos(TimeUnit.DAYS.toNanos(1));
        if (Instant.now().isAfter(tomorrow)) {
            int totalSharePoint = entity.getSharesCount() * 7;
            int totalLikePoint = entity.getLikesCount() * 10;
            int totalSavePoint = entity.getSavesCount() * 5;
            if ((totalSavePoint + totalLikePoint + totalSharePoint + entity.getViewsCount()) >= 10000) {
                entity.setRatingType(RatingTypeEnum.POPULAR.getValue());
            } else if ((totalSavePoint + totalLikePoint + totalSharePoint + entity.getViewsCount()) >= 1000) {
                entity.setRatingType(RatingTypeEnum.RECOMMENDED.getValue());
            } else if ((totalSavePoint + totalLikePoint + totalSharePoint + entity.getViewsCount()) >= 100) {
                entity.setRatingType(RatingTypeEnum.NORMAL.getValue());
            } else {
                entity.setRatingType(RatingTypeEnum.LOW.getValue());
            }
            entity.setUpdatedAt(Instant.now());
            entity.setUpdatedBy("-1");
            repository.save(entity);
        }
    }

    @Override
    public void updateRatingCount(String postId, RatingPointEnum pointEnum) {
        validator.validKey(postId);
        PostRatingEntity entity = repository.findByPostIdQuery(postId).orElseThrow(() -> {
            throw new NotFoundException("Post Rating not found");
        });
        switch (pointEnum) {
            case LIKE -> entity.setLikesCount(entity.getLikesCount() + 1);
            case SAVE -> entity.setSavesCount(entity.getSavesCount() + 1);
            case SHARE -> entity.setSharesCount(entity.getSharesCount() + 1);
            case VIEW -> entity.setViewsCount(entity.getViewsCount() + 1);
        }
        repository.save(entity);
    }

    @Override
    public void delete(String postId) {
        validator.validKey(postId);
        if (!repository.existsById(postId)) {
            throw new NotFoundException("Post Rating not found");
        }
        repository.deleteById(postId);
    }

    @Override
    public PostRatingGetDTO get(String id) {
        validator.validKey(id);
        return returnToGetDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post Rating not found");
                        })
        );
    }

    @Override
    public PostRatingDetailDTO detail(String id) {
        validator.validKey(id);
        return mapper.fromDetailDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post Rating not found");
                        })
        );
    }

    @Override
    public List<PostRatingGetDTO> list(PostRatingCriteria criteria) {
        return repository.findAll(
                        PageRequest.of(criteria.getPage(), criteria.getSize())
                ).stream()
                .map(this::returnToGetDTO)
                .toList();
    }

    private PostRatingGetDTO returnToGetDTO(PostRatingEntity entity) {
        PostRatingGetDTO postRatingGetDTO = mapper.fromGetDTO(entity);
        postRatingGetDTO.setRatingPostId(entity.getPostId().getId());
        postRatingGetDTO.setRatingType(entity.getRatingType());
        return postRatingGetDTO;
    }
}
