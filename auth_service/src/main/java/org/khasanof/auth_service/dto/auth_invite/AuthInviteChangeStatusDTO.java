package org.khasanof.auth_service.dto.auth_invite;

import lombok.*;
import org.khasanof.auth_service.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthInviteChangeStatusDTO extends GenericDTO {
    private String requestUserId;
    private String status;
}
