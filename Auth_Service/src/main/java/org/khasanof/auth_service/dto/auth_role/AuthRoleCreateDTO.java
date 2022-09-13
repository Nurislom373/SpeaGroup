package org.khasanof.auth_service.dto.auth_role;

import lombok.*;
import org.khasanof.auth_service.dto.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRoleCreateDTO implements BaseDTO {
    private String authId;
    private String role;
}
