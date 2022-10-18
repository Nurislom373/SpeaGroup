package org.khasanof.auth_service.dto.auth_invite;

import lombok.*;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.entity.invite.InviteEntity;

import java.time.Instant;
import java.util.LinkedList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthInviteDetailDTO extends GenericDTO {
    private AuthUserEntity userId;
    private LinkedList<InviteEntity> invites;
    private Instant updatedAt;
    private Instant createdAt;
}
