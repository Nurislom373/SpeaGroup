package org.khasanof.auth_service.entity.invite;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InviteEntity {
    private String userId;
    private Instant requestTime;
    private String status;
    private Instant acceptTime;

    public InviteEntity(String userId) {
        this.userId = userId;
        this.requestTime = Instant.now();
    }
}
