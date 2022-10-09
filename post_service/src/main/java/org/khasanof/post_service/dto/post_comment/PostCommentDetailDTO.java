package org.khasanof.post_service.dto.post_comment;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.entity.comment.CommentEntity;
import org.khasanof.post_service.entity.post.PostEntity;

import java.time.Instant;
import java.util.LinkedList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostCommentDetailDTO extends GenericDTO {
    private PostEntity post;
    private LinkedList<CommentEntity> comments;
    private Integer commentsCount;
    private String lastUpdateType;
    private Instant updatedAt;
    private Instant createdAt;
    private String createdBy;
    private boolean isDeleted;

    public PostCommentDetailDTO(LinkedList<CommentEntity> comments, Integer commentsCount) {
        this.comments = comments;
        this.commentsCount = commentsCount;
    }
}
