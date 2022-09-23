package org.khasanof.auth_service.dto.token;

import lombok.*;
import org.khasanof.auth_service.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDTO extends GenericDTO {
    private String accessToken;
    private String refreshToken;
}
