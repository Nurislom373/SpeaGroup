package org.khasanof.question_service.entity.answer;

import lombok.*;
import org.khasanof.question_service.entity.Auditable;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

/**
 * @author Nurislom
 * Date: 12/4/2022
 * Time: 5:54 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class AnswerEntity extends Auditable {
    private String id;
    @Field(name = "user_id")
    private String userId;
    private String message;
    @Field(name = "is_true")
    private boolean isTrue;
    @Field(name = "is_deleted")
    private boolean isDeleted;
    @CreatedDate
    @Field(name = "created_at")
    private Instant createdAt = Instant.now();
}
