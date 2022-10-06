package org.khasanof.post_service.service.post_comment;

import org.khasanof.post_service.dto.post_comment.PostCommentAddLikeDTO;
import org.khasanof.post_service.dto.post_comment.PostCommentCreateDTO;
import org.khasanof.post_service.dto.post_comment.PostCommentRemoveLikeDTO;

public interface PostCommentCDADService {

    void create(PostCommentCreateDTO dto);

    void delete(String postId, String commentId);

    void addCommentToLike(PostCommentAddLikeDTO dto);

    void deleteCommentToLike(PostCommentRemoveLikeDTO dto);

}
