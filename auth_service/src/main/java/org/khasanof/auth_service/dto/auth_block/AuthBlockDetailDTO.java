package org.khasanof.auth_service.dto.auth_block;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.entity.blocked_for.BlockedForEntity;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthBlockDetailDTO extends GenericDTO {
    private AuthUserEntity userId;
    private Instant duration;
    private BlockedForEntity blockedFor;
}
