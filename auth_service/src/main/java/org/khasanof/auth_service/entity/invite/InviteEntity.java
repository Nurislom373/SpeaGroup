package org.khasanof.auth_service.entity.invite;

import lombok.*;
import org.khasanof.auth_service.enums.auth_invite.AuthInviteStatusEnum;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InviteEntity {
    private String userId;
    private Instant requestTime;
    private AuthInviteStatusEnum status;
    private Instant acceptTime;

    public InviteEntity(String userId, AuthInviteStatusEnum status) {
        this.userId = userId;
        this.status = status;
        this.requestTime = Instant.now();
    }
}
