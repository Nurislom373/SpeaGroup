package org.khasanof.auth_service.dto.auth_invite;

import lombok.*;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthInviteDetailDTO extends GenericDTO {
    private AuthUserEntity user;
    private AuthUserEntity requestUser;
    private String status;
    private Instant requestTime;
    private Instant acceptTime;
}
