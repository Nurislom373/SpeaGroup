package org.khasanof.auth_service.dto.auth;

import lombok.*;
import org.khasanof.auth_service.annotation.MongoIdConstraint;
import org.khasanof.auth_service.dto.GenericDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthChangePasswordDTO extends GenericDTO {
    @NotBlank
    @MongoIdConstraint
    private String userId;
    @NotBlank
    @Size(min = 4, max = 120)
    private String oldPwd;
    @NotBlank
    @Size(min = 4, max = 120)
    private String newPwd;
}
