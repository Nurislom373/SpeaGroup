package org.khasanof.auth_service.dto.auth_token;

import lombok.*;
import org.khasanof.auth_service.dto.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthTokenCreateDTO implements BaseDTO {
    private String authId;
    private String token;
    private Integer minTime;
}
