package org.khasanof.auth_service.dto.auth_invite;

import lombok.*;
import org.khasanof.auth_service.annotation.MongoIdConstraint;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.enums.auth_invite.AuthInviteStatusEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthInviteChangeStatusDTO extends GenericDTO {
    @NotBlank
    @MongoIdConstraint
    private String requestUserId;
    @NotNull
    private AuthInviteStatusEnum status;
}
