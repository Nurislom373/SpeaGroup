package org.khasanof.auth_service.dto.auth_token;

import lombok.*;
import org.khasanof.auth_service.annotation.MongoIdConstraint;
import org.khasanof.auth_service.dto.BaseDTO;
import org.khasanof.auth_service.enums.auth_token.AuthTokenType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenCreateDTO implements BaseDTO {
    @NotBlank
    @MongoIdConstraint
    private String authId;
    @NotNull
    private AuthTokenType type;
    @NotBlank
    private String token;
    @NotNull
    @Min(value = 1)
    private Integer minTime;
}
