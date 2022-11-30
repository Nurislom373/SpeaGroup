package org.khasanof.post_service.service.post_comment;

import org.khasanof.post_service.dto.post_comment.*;
import org.khasanof.post_service.entity.post.PostEntity;

public interface PostCommentCDADService {

    void create(PostEntity entity);

    void addComment(PostCommentCreateDTO dto);

    void delete(String id);

    void deleteComment(String postId, String commentId);

    void addCommentToLike(PostCommentAddLikeDTO dto);

    void deleteCommentToLike(PostCommentRemoveLikeDTO dto);

    PostCommentGetDTO getByPostId(String id);

    PostCommentDetailDTO detailCommentsCount(String id, Integer count);

    PostCommentCount getCommentsCount(String id);
}
