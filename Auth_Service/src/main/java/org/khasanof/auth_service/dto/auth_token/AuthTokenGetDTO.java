package org.khasanof.auth_service.dto.auth_token;

import lombok.*;
import org.khasanof.auth_service.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthTokenGetDTO extends GenericDTO {
    private String token;
}
