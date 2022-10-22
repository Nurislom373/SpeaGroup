package org.khasanof.auth_service.dto.auth_token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.enums.auth_token.AuthTokenType;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenDetailDTO extends GenericDTO {
    private AuthUserEntity userId;
    private String token;
    private AuthTokenType type;
    private Instant duration;
    private boolean isDead;
}
