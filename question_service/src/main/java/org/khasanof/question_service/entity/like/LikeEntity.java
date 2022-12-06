package org.khasanof.question_service.entity.like;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikeEntity {
    @Field(name = "user_id")
    private String userId;
    private String type;
    @CreatedDate
    @Field(name = "created_at")
    private Instant createdAt;

    public LikeEntity(String userId, String type) {
        this.userId = userId;
        this.type = type;
        this.createdAt = Instant.now();
    }
}
