package org.khasanof.post_service.entity.post_comment;

import lombok.*;
import org.khasanof.post_service.entity.Auditable;
import org.khasanof.post_service.entity.comment.CommentEntity;
import org.khasanof.post_service.entity.post.PostEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.LinkedList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "post_comment")
public class PostCommentEntity extends Auditable {
    @DocumentReference
    @Field(name = "post_id")
    private PostEntity postId;
    private LinkedList<CommentEntity> comments;
    private String lastUpdateType;
}
