package org.khasanof.auth_service.dto.auth_role;

import lombok.*;
import org.khasanof.auth_service.annotation.MongoIdConstraint;
import org.khasanof.auth_service.dto.BaseDTO;
import org.khasanof.auth_service.enums.auth_role.AuthRoleEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRoleCreateDTO implements BaseDTO {
    @NotBlank
    @MongoIdConstraint
    private String authId;
    @NotNull
    private AuthRoleEnum role;
}
