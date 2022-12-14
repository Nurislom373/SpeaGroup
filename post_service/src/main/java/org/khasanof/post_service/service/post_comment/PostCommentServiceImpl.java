package org.khasanof.post_service.service.post_comment;

import org.khasanof.post_service.criteria.post_comment.PostCommentCriteria;
import org.khasanof.post_service.dto.auth_user.AuthUserGetDTO;
import org.khasanof.post_service.dto.post_comment.*;
import org.khasanof.post_service.entity.comment.CommentEntity;
import org.khasanof.post_service.entity.like.LikeEntity;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.entity.post_comment.PostCommentEntity;
import org.khasanof.post_service.enums.comment.CommentLastUpdateType;
import org.khasanof.post_service.enums.like.LikeTypeEnum;
import org.khasanof.post_service.mapper.post_comment.PostCommentMapper;
import org.khasanof.post_service.repository.post_comment.PostCommentRepository;
import org.khasanof.post_service.service.AbstractService;
import org.khasanof.post_service.service.post.PostService;
import org.khasanof.post_service.utils.BaseUtils;
import org.khasanof.post_service.validator.post_comment.PostCommentValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.webjars.NotFoundException;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Service
public class PostCommentServiceImpl extends AbstractService<PostCommentRepository, PostCommentMapper, PostCommentValidator> implements PostCommentService {

    private final PostService postService;
    private final MongoTemplate mongoTemplate;

    public PostCommentServiceImpl(PostCommentRepository repository, PostCommentMapper mapper, PostCommentValidator validator, PostService postService, MongoTemplate mongoTemplate) {
        super(repository, mapper, validator);
        this.postService = postService;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void create(PostEntity entity) {
        PostCommentEntity commentEntity = mongoTemplate.findOne(
                Query.query(new Criteria("postId")
                        .is(entity)),
                PostCommentEntity.class);
        if (Objects.isNull(commentEntity)) {
            PostCommentEntity postCommentEntity = new PostCommentEntity(entity, new LinkedList<>());
            repository.save(postCommentEntity);
        } else {
            throw new RuntimeException("Already Created Post Comment!");
        }
    }

    @Override
    public void addComment(PostCommentCreateDTO dto) {
        validator.validCreateDTO(dto);
        PostEntity entity = postService.getEntity(dto.getCommentPostId());
        PostCommentEntity commentEntity = mongoTemplate.findOne(
                Query.query(new Criteria("postId")
                        .is(entity)),
                PostCommentEntity.class);
        if (Objects.isNull(commentEntity)) {
            throw new RuntimeException("PostComment not found");
        }
        LinkedList<CommentEntity> comments;
        if (Objects.isNull(commentEntity.getComments())) {
            comments = new LinkedList<>();
        } else {
            comments = commentEntity.getComments();
        }
        comments.add(new CommentEntity(String.valueOf(System.currentTimeMillis()), dto.getUserId(),
                dto.getReplyId(), dto.getMessage()));
        commentEntity.setComments(comments);
        commentEntity.setLastUpdateType(CommentLastUpdateType.ADD_COMMENT);
        commentEntity.setUpdatedAt(Instant.now());
        repository.save(commentEntity);
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id)) {
            throw new NotFoundException("Post Comment not found");
        }
        repository.deleteById(id);
    }

    @Override
    public void deleteComment(String postId, String commentId) {
        validator.validKey(postId);
        validator.validKey(commentId);
        PostCommentEntity commentEntity = repository.findById(postId)
                .orElseThrow(() -> {
                    throw new NotFoundException("Post Comment not found");
                });
        LinkedList<CommentEntity> comments = commentEntity.getComments();
        if (!comments.removeIf(f -> f.getId().equals(commentId)))
            throw new NotFoundException("Comment not found");
        commentEntity.setComments(comments);
        repository.save(commentEntity);
    }

    @Override
    public void addCommentToLike(PostCommentAddLikeDTO dto) {
        validator.validUpdateDTO(dto);
        PostCommentEntity commentEntity = repository.findById(dto.getId())
                .orElseThrow(() -> {
                    throw new NotFoundException("Post Comment Not found");
                });
        LinkedList<CommentEntity> comments = commentEntity.getComments();
        CommentEntity comment = comments.stream()
                .filter(f -> f.getId().equals(dto.getCommentId()))
                .findFirst().orElseThrow(() -> {
                    throw new NotFoundException("Comment not found");
                });
        if (!LikeTypeEnum.hasLikeType(dto.getType())) {
            throw new RuntimeException("Like type is Invalid");
        }
        comments.remove(comment);
        LinkedList<LikeEntity> likes = comment.getLikes();
        likes.add(new LikeEntity(dto.getUserId(), dto.getType()));
        comment.setLikes(likes);
        commentEntity.setComments(comments);
        commentEntity.setLastUpdateType(CommentLastUpdateType.ADD_LIKE);
        repository.save(commentEntity);
    }

    @Override
    public void deleteCommentToLike(PostCommentRemoveLikeDTO dto) {
        validator.validCommentRemoveDTO(dto);
        PostCommentEntity commentEntity = repository.findById(dto.getId())
                .orElseThrow(() -> {
                    throw new NotFoundException("Post Comment Not found");
                });
        LinkedList<CommentEntity> comments = commentEntity.getComments();
        CommentEntity comment = comments.stream()
                .filter(f -> f.getId().equals(dto.getCommentId()))
                .findFirst().orElseThrow(() -> {
                    throw new NotFoundException("Comment not found");
                });
        comments.remove(comment);
        LinkedList<LikeEntity> likes = comment.getLikes();
        if (!likes.removeIf(l -> l.getUserId().equals(dto.getUserId()))) {
            throw new NotFoundException("UserId not found");
        }
        comment.setLikes(likes);
        commentEntity.setComments(comments);
        commentEntity.setLastUpdateType(CommentLastUpdateType.REMOVE_LIKE);
        repository.save(commentEntity);
    }

    @Override
    public PostCommentGetDTO getByPostId(String id) {
        validator.validKey(id);
        return returnGetDTO(
                repository.findByPostIdQuery(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post Comment not found");
                        })
        );
    }

    @Override
    public PostCommentGetDTO get(String id) {
        validator.validKey(id);
        return returnGetDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post Comment not found");
                        }));
    }

    @Override
    public PostCommentDetailDTO detail(String id) {
        validator.validKey(id);
        return mapper.fromDetailDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post Comment not found");
                        })
        );
    }

    @Override
    public PostCommentDetailDTO detailCommentsCount(String id, Integer count) {
        validator.validKey(id);
        Assert.notNull(count, "count must be not null!");
        PostCommentEntity comment = repository.findByPostIdQuery(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Post Comment not found");
                });
        LinkedList<CommentEntity> comments = comment.getComments();
        if (comments.size() >= 20) {
            LinkedList<CommentEntity> countComments = new LinkedList<>();
            IntStream.range(0, 10).forEach(i -> {
                countComments.add(comments.get(i));
            });
            return new PostCommentDetailDTO(countComments, countComments.size());
        } else {
            return new PostCommentDetailDTO(comments, comments.size());
        }
    }

    @Override
    public PostCommentCount getCommentsCount(String id) {
        validator.validKey(id);
        return repository.findByIdCountQuery(id);
    }

    @Override
    public List<PostCommentGetDTO> list(PostCommentCriteria criteria) {
        return repository.findAll(
                        PageRequest.of(
                                criteria.getPage(), criteria.getSize()
                        )).stream()
                .map(this::returnGetDTO)
                .toList();
    }

    private PostCommentGetDTO returnGetDTO(PostCommentEntity entity) {
        PostCommentGetDTO postCommentGetDTO = mapper.fromGetDTO(entity);
        postCommentGetDTO.setCommentPostId(entity.getId());
        postCommentGetDTO.setCommentsCount(entity.getComments().size());
        return postCommentGetDTO;
    }

    private AuthUserGetDTO getAuthDTO(String id) {
        return BaseUtils.callGetAPI(
                BaseUtils.AUTH_SERVICE + "/auth_user/get/" + id, "User ont found", new AuthUserGetDTO());
    }
}
