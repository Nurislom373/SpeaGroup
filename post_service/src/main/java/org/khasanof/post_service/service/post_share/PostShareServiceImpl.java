package org.khasanof.post_service.service.post_share;

import org.khasanof.post_service.criteria.post_share.PostShareCriteria;
import org.khasanof.post_service.dto.post_share.PostShareCreateDTO;
import org.khasanof.post_service.dto.post_share.PostShareDetailDTO;
import org.khasanof.post_service.dto.post_share.PostShareGetDTO;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.entity.post_save.PostSaveEntity;
import org.khasanof.post_service.entity.post_share.PostShareEntity;
import org.khasanof.post_service.entity.share.ShareEntity;
import org.khasanof.post_service.enums.rating.RatingPointEnum;
import org.khasanof.post_service.mapper.post.PostMapper;
import org.khasanof.post_service.mapper.post_share.PostShareMapper;
import org.khasanof.post_service.repository.post_share.PostShareRepository;
import org.khasanof.post_service.service.AbstractService;
import org.khasanof.post_service.service.post.PostService;
import org.khasanof.post_service.service.post_rating.PostRatingService;
import org.khasanof.post_service.utils.BaseUtils;
import org.khasanof.post_service.validator.post_share.PostShareValidator;
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
public class PostShareServiceImpl extends AbstractService<PostShareRepository, PostShareMapper, PostShareValidator> implements PostShareService {

    private final PostService postService;
    private final PostRatingService postRatingService;
    private final MongoTemplate mongoTemplate;

    public PostShareServiceImpl(PostShareRepository repository, PostShareMapper mapper, PostShareValidator validator, PostService postService, PostRatingService postRatingService, MongoTemplate mongoTemplate) {
        super(repository, mapper, validator);
        this.postService = postService;
        this.postRatingService = postRatingService;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void create(PostShareCreateDTO dto) {
        validator.validCreateDTO(dto);
        PostShareEntity shareEntity = mongoTemplate.findOne(
                Query.query(new Criteria("postId")
                        .is(postService.getEntity(dto.getSharePostId()))),
                PostShareEntity.class);
        if (Objects.nonNull(shareEntity)) {
            LinkedList<ShareEntity> shares = shareEntity.getShares();
            boolean anyMatch = shares.stream()
                    .anyMatch(f -> f.getUserId().equals(dto.getUserId()));
            if (!anyMatch) {
                shares.add(new ShareEntity(dto.getUserId(), dto.getType()));
                shareEntity.setShares(shares);
                shareEntity.setUpdatedAt(Instant.now());
                shareEntity.setUpdatedBy("-1");
                repository.save(shareEntity);
                BaseUtils.EXECUTOR_SERVICE.execute(() ->
                        postRatingService.updateRatingCount(dto.getSharePostId(),
                                RatingPointEnum.SHARE, false));
            }
        } else {
            if (postService.existByIdAndCheckBlocked(dto.getSharePostId())) {
                throw new RuntimeException("This Post is block");
            }
            PostShareEntity postShareEntity = mapper.toCreateDTO(dto);
            postShareEntity.setPostId(postService.getEntity(dto.getSharePostId()));
            LinkedList<ShareEntity> shares = new LinkedList<>();
            shares.add(new ShareEntity(dto.getUserId(), dto.getType()));
            postShareEntity.setShares(shares);
            repository.save(postShareEntity);
            BaseUtils.EXECUTOR_SERVICE.execute(() ->
                    postRatingService.updateRatingCount(dto.getSharePostId(),
                            RatingPointEnum.SHARE, false));
        }
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id)) {
            throw new NotFoundException("Post Share not found");
        }
        repository.deleteById(id);
    }

    @Override
    public PostShareGetDTO getByPostId(String id) {
        validator.validKey(id);
        return returnToGetDTO(
                repository.findByPostIdQuery(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post Share not found");
                        })
        );
    }

    @Override
    public PostShareGetDTO get(String id) {
        validator.validKey(id);
        return returnToGetDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post Share not found");
                        })
        );
    }

    @Override
    public PostShareDetailDTO detail(String id) {
        validator.validKey(id);
        return mapper.fromDetailDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post Share not found");
                        })
        );
    }

    @Override
    public List<PostShareGetDTO> list(PostShareCriteria criteria) {
        return repository.findAll(
                        PageRequest.of(criteria.getPage(), criteria.getSize())
                )
                .stream()
                .map(this::returnToGetDTO)
                .toList();
    }

    private PostShareGetDTO returnToGetDTO(PostShareEntity entity) {
        PostShareGetDTO postShareGetDTO = mapper.fromGetDTO(entity);
        postShareGetDTO.setSharePostId(entity.getPostId().getId());
        postShareGetDTO.setSharesCount(entity.getShares().size());
        return postShareGetDTO;
    }
}
