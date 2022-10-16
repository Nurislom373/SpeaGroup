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
    private String requestUserId;
    private Instant requestTime;
    private Instant acceptTime;
}
