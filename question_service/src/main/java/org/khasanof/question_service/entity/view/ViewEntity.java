package org.khasanof.question_service.entity.view;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
