package org.khasanof.question_service.entity;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class Auditable implements BaseEntity {
    @Id
    private String id;

    @Field(name = "is_deleted")
    private boolean isDeleted;

    @CreatedDate
    @Field(name = "created_at")
    private Instant createdAt = Instant.now();

    @CreatedBy
    private String createdBy = "-1";

    @LastModifiedDate
    private Instant updatedAt;

    @LastModifiedBy
    private String updatedBy;
}
