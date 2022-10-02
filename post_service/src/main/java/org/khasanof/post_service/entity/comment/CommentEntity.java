package org.khasanof.post_service.entity.comment;

import lombok.*;
import org.khasanof.post_service.entity.Auditable;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
}
