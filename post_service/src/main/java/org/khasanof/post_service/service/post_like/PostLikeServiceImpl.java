package org.khasanof.post_service.service.post_like;

import org.khasanof.post_service.criteria.post_like.PostLikeCriteria;
import org.khasanof.post_service.dto.post_like.*;
import org.khasanof.post_service.entity.like.LikeEntity;
import org.khasanof.post_service.entity.post_like.PostLikeEntity;
import org.khasanof.post_service.enums.like.LikeTypeEnum;
import org.khasanof.post_service.enums.rating.RatingPointEnum;
import org.khasanof.post_service.mapper.post_like.PostLikeMapper;
import org.khasanof.post_service.repository.post_like.PostLikeRepository;
import org.khasanof.post_service.service.AbstractService;
import org.khasanof.post_service.service.post.PostService;
import org.khasanof.post_service.service.post_rating.PostRatingService;
import org.khasanof.post_service.validator.post_like.PostLikeValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

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
    public void create(PostLikeCreateDTO dto) {
        validator.validCreateDTO(dto);
        PostLikeEntity postLike = mongoTemplate.findOne(
                Query.query(new Criteria("postId")
                        .is(postService.getEntity(dto.getLikePostId()))),
                PostLikeEntity.class);
        if (Objects.isNull(postLike)) {
            if (!postService.existByIdAndCheckBlocked(dto.getLikePostId())) {
                throw new NotFoundException("Post not found");
            }
            PostLikeEntity postLikeEntity = mapper.toCreateDTO(dto);
            LinkedList<LikeEntity> likes = new LinkedList<>();
            likes.add(new LikeEntity(dto.getUserId(), dto.getType()));
            postLikeEntity.setLikes(likes);
            repository.save(postLikeEntity);
        } else {
            LinkedList<LikeEntity> likes = postLike.getLikes();
            boolean anyMatch = likes.stream()
                    .anyMatch(a -> a.getUserId()
                            .equals(dto.getUserId()));
            if (anyMatch) {
                likes.add(new LikeEntity(dto.getUserId(), dto.getType()));
                postLike.setLikes(likes);
                repository.save(postLike);
            }
        }
        postRatingService.updateRatingCount(dto.getLikePostId(), RatingPointEnum.LIKE, false);
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
        postRatingService.updateRatingCount(dto.getLikePostId(), RatingPointEnum.LIKE, true);
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
