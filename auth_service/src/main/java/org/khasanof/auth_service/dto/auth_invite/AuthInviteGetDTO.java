package org.khasanof.auth_service.dto.auth_invite;

import lombok.*;
import org.khasanof.auth_service.dto.GenericDTO;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthInviteGetDTO extends GenericDTO {
    private String authUserId;
    private List<String> inviteIds;
    private Instant updatedAt;
    private Instant createdAt;
}
