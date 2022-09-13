package org.khasanof.auth_service.dto.auth_role;

import lombok.*;
import org.khasanof.auth_service.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRoleGetDTO extends GenericDTO {
    private String authId;
    private String role;
}
