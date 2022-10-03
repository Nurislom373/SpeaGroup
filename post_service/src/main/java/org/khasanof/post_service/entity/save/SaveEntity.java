package org.khasanof.post_service.entity.save;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SaveEntity {
    private String userId;
    private Instant createdAt;
}
