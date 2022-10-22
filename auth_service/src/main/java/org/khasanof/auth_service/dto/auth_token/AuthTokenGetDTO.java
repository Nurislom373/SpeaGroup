package org.khasanof.auth_service.dto.auth_token;

import lombok.*;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.enums.auth_token.AuthTokenType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenGetDTO extends GenericDTO {
    private String authUserId;
    private String token;
    private AuthTokenType type;
}
