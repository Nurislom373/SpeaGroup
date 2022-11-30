package org.khasanof.post_service.entity.post_comment;

import lombok.*;
import org.khasanof.post_service.entity.Auditable;
import org.khasanof.post_service.entity.comment.CommentEntity;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.enums.comment.CommentLastUpdateType;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.LinkedList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "post_comment")
public class PostCommentEntity extends Auditable {
    @DocumentReference
    @Field(name = "post_id")
    private PostEntity postId;
    private LinkedList<CommentEntity> comments;
    private CommentLastUpdateType lastUpdateType;

    public PostCommentEntity(PostEntity postId, LinkedList<CommentEntity> list) {
        this.postId = postId;
        this.comments = list;
    }
}
