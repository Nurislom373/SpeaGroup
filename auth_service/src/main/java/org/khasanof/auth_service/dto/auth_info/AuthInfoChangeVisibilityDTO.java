package org.khasanof.auth_service.dto.auth_info;

import lombok.*;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.enums.auth_info.AuthInfoVisibilityEnum;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthInfoChangeVisibilityDTO extends GenericDTO {
    @NotBlank
    private AuthInfoVisibilityEnum visibility;
}
