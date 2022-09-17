package org.khasanof.auth_service.dto.auth_token;

import lombok.*;
import org.khasanof.auth_service.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenUpdateDTO extends GenericDTO {
    private String token;
    private Integer minTime;
}