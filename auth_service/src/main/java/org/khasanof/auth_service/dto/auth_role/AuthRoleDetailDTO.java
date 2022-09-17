package org.khasanof.auth_service.dto.auth_role;

import lombok.*;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRoleDetailDTO extends GenericDTO {
    private AuthUserEntity user;
    private String role;
}
