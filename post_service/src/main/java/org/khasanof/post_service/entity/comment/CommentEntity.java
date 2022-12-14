package org.khasanof.post_service.entity.comment;

import lombok.*;
import org.khasanof.post_service.entity.like.LikeEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;
import java.util.LinkedList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CommentEntity {
    private String id;
    @Field(name = "user_id")
    private String userId;
    @Field(name = "reply_id")
    private String replyId;
    private String message;
    @Field(name = "is_deleted")
    private boolean isDeleted;
    @Field(name = "send_time")
    private Instant sendTime;
    @Field(name = "delete_time")
    private Instant deleteTime;
    private LinkedList<LikeEntity> likes;
    private Long likesCount;
    private Instant updateTime;

    public CommentEntity(String id, String userId, String replyId, String message) {
        this.id = id;
        this.userId = userId;
        this.replyId = replyId;
        this.message = message;
    }
}
