package org.khasanof.post_service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Auditable implements BaseEntity {

    @MongoId(FieldType.OBJECT_ID)
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
