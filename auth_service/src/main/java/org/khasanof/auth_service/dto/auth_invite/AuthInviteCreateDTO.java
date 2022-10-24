package org.khasanof.auth_service.dto.auth_invite;

import lombok.*;
import org.khasanof.auth_service.annotation.MongoIdConstraint;
import org.khasanof.auth_service.dto.BaseDTO;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthInviteCreateDTO implements BaseDTO {
    @NotBlank
    @MongoIdConstraint
    private String inviteUserId;
    @NotBlank
    @MongoIdConstraint
    private String requestUserId;
}
