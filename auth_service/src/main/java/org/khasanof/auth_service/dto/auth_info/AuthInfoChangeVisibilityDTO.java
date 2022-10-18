package org.khasanof.auth_service.dto.auth_info;

import lombok.*;
import org.khasanof.auth_service.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthInfoChangeVisibilityDTO extends GenericDTO {
    private String visibility;
}
