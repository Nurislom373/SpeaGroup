package org.khasanof.auth_service.dto.auth_follower;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthFollowerDetailDTO extends GenericDTO {
    private AuthUserEntity user;
    public List<AuthUserEntity> followers;
}
