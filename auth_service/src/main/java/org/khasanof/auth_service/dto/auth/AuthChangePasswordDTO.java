package org.khasanof.auth_service.dto.auth;

import lombok.*;
import org.khasanof.auth_service.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthChangePasswordDTO extends GenericDTO {
    private String userId;
    private String oldPwd;
    private String newPwd;
}
