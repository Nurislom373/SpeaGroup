package org.khasanof.post_service.entity.view;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@ToString
public class ViewEntity {
    private String userId;
    private Instant createdAt;

    public ViewEntity(String userId) {
        this.userId = userId;
        this.createdAt = Instant.now();
    }
}
