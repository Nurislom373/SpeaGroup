package org.khasanof.post_service.service.post_comment;

import org.khasanof.post_service.criteria.post_comment.PostCommentCriteria;
import org.khasanof.post_service.dto.auth_user.AuthUserGetDTO;
import org.khasanof.post_service.dto.post_comment.*;
import org.khasanof.post_service.entity.auth_user.AuthUserEntity;
import org.khasanof.post_service.entity.comment.CommentEntity;
import org.khasanof.post_service.entity.like.LikeEntity;
import org.khasanof.post_service.entity.post_comment.PostCommentEntity;
import org.khasanof.post_service.enums.comment.CommentLastUpdateType;
import org.khasanof.post_service.enums.like.LikeTypeEnum;
import org.khasanof.post_service.mapper.post_comment.PostCommentMapper;
import org.khasanof.post_service.repository.post.PostRepository;
import org.khasanof.post_service.repository.post_comment.PostCommentRepository;
import org.khasanof.post_service.service.AbstractService;
import org.khasanof.post_service.utils.BaseUtils;
import org.khasanof.post_service.validator.post_comment.PostCommentValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class PostCommentServiceImpl extends AbstractService<PostCommentRepository, PostCommentMapper, PostCommentValidator> implements PostCommentService {
    private final PostRepository postRepository;

    public PostCommentServiceImpl(PostCommentRepository repository, PostCommentMapper mapper, PostCommentValidator validator, PostRepository postRepository) {
        super(repository, mapper, validator);
        this.postRepository = postRepository;
    }

    @Override
    public void create(PostCommentCreateDTO dto) {
        validator.validCreateDTO(dto);
        if (!postRepository.existsById(dto.getCommentPostId())) {
            throw new NotFoundException("Post not found");
        }
        PostCommentEntity postComment = repository.findByQuery(dto.getCommentPostId());
        if (Objects.isNull(postComment)) {
            AuthUserEntity user = new AuthUserEntity();
            BeanUtils.copyProperties(getAuthDTO(dto.getUserId()), user);
            PostCommentEntity postCommentEntity = mapper.toCreateDTO(dto);
            postCommentEntity.setPostId(postRepository.findById(dto.getCommentPostId()).get());
            postCommentEntity.setComments(new LinkedList<>());
            postCommentEntity.getComments().add(new CommentEntity(String.valueOf(System.currentTimeMillis()), user.getId(), dto.getReplyId(), dto.getMessage()));
            repository.insert(postCommentEntity);
        } else {
            AuthUserEntity user = new AuthUserEntity();
            BeanUtils.copyProperties(getAuthDTO(dto.getUserId()), user);
            LinkedList<CommentEntity> comments = postComment.getComments();
            comments.add(new CommentEntity(String.valueOf(System.currentTimeMillis()), user.getId(), dto.getReplyId(), dto.getMessage()));
            postComment.setComments(comments);
            postComment.setLastUpdateType(CommentLastUpdateType.ADD_COMMENT.getValue());
            postComment.setUpdatedAt(Instant.now());
            postComment.setUpdatedBy("-1");
            repository.save(postComment);
        }
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
        PostCommentEntity commentEntity = repository.findById(postId).orElseThrow(() -> {
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
        PostCommentEntity commentEntity = repository.findById(dto.getId()).orElseThrow(() -> {
            throw new NotFoundException("Post Comment Not found");
        });
        LinkedList<CommentEntity> comments = commentEntity.getComments();
        CommentEntity comment = comments.stream().filter(f -> f.getId().equals(dto.getCommentId())).findFirst().orElseThrow(() -> {
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
        commentEntity.setLastUpdateType(CommentLastUpdateType.ADD_LIKE.getValue());
        repository.save(commentEntity);
    }

    @Override
    public void deleteCommentToLike(PostCommentRemoveLikeDTO dto) {
        validator.validCommentRemoveDTO(dto);
        PostCommentEntity commentEntity = repository.findById(dto.getId()).orElseThrow(() -> {
            throw new NotFoundException("Post Comment Not found");
        });
        LinkedList<CommentEntity> comments = commentEntity.getComments();
        CommentEntity comment = comments.stream().filter(f -> f.getId().equals(dto.getCommentId())).findFirst().orElseThrow(() -> {
            throw new NotFoundException("Comment not found");
        });
        comments.remove(comment);
        LinkedList<LikeEntity> likes = comment.getLikes();
        if (!likes.removeIf(l -> l.getUserId().equals(dto.getUserId()))) {
            throw new NotFoundException("UserId not found");
        }
        comment.setLikes(likes);
        commentEntity.setComments(comments);
        commentEntity.setLastUpdateType(CommentLastUpdateType.REMOVE_LIKE.getValue());
        repository.save(commentEntity);
    }

    @Override
    public PostCommentGetDTO get(String id) {
        return null;
    }

    @Override
    public PostCommentDetailDTO detail(String id) {
        return null;
    }

    @Override
    public List<PostCommentGetDTO> list(PostCommentCriteria criteria) {
        return null;
    }

    private AuthUserGetDTO getAuthDTO(String id) {
        return BaseUtils.callGetAPI(
                BaseUtils.AUTH_SERVICE + "/auth_user/get/" + id, "User ont found");
    }
}
