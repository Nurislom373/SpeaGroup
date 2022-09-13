package org.khasanof.auth_service.dto.auth_token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenDetailDTO extends GenericDTO {
    private AuthUserEntity user;
    private String token;
    private Instant duration;
}
