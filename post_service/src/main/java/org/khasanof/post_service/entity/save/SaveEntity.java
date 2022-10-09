package org.khasanof.post_service.entity.save;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@ToString
public class SaveEntity {
    private String userId;
    private Instant createdAt;

    public SaveEntity(String userId) {
        this.userId = userId;
        this.createdAt = Instant.now();
    }
}
