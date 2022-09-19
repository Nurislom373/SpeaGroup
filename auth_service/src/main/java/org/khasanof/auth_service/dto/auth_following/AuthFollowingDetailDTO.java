package org.khasanof.auth_service.dto.auth_following;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.entity.auth_following.AuthFollowingEntity;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthFollowingDetailDTO extends GenericDTO {
    private AuthFollowingEntity authId;
    private List<AuthFollowingEntity
            > followingsId;
}
