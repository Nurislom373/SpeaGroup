package org.khasanof.auth_service.dto.auth_invite;

import lombok.*;
import org.khasanof.auth_service.dto.GenericDTO;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthInviteGetDTO extends GenericDTO {
    private String userId;
    private String requestUserId;
    private String status;
    private Instant requestTime;
    private Instant acceptTime;
}
