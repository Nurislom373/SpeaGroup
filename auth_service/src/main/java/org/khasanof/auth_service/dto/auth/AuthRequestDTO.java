package org.khasanof.auth_service.dto.auth;

import lombok.*;
import org.khasanof.auth_service.dto.BaseDTO;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthRequestDTO implements BaseDTO {
    @NotBlank
    private String emailOrUsername;
    @NotBlank
    private String password;
}
