package org.khasanof.auth_service.dto.auth_token;

import lombok.*;
import org.khasanof.auth_service.annotation.IsMongoId;
import org.khasanof.auth_service.dto.BaseDTO;
import org.khasanof.auth_service.enums.auth_token.AuthTokenType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenCreateDTO implements BaseDTO {
    @NotBlank
    @IsMongoId
    private String authId;
    private AuthTokenType type;
    private String token;
    private Integer minTime;
}
