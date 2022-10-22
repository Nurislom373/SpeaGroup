package org.khasanof.auth_service.dto.auth_token;

import lombok.*;
import org.khasanof.auth_service.dto.BaseDTO;
import org.khasanof.auth_service.enums.auth_token.AuthTokenType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenCreateDTO implements BaseDTO {
    private String authId;
    private AuthTokenType type;
    private String token;
    private Integer minTime;
}
