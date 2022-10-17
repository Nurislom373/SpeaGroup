package org.khasanof.auth_service.dto.auth_invite;

import lombok.*;
import org.khasanof.auth_service.dto.BaseDTO;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthInviteCreateDTO implements BaseDTO {
    @NotBlank
    private String userId;
    @NotBlank
    private String requestUserId;
}
