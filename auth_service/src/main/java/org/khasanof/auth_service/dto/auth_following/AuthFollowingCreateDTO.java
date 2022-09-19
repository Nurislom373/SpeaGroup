package org.khasanof.auth_service.dto.auth_following;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.BaseDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthFollowingCreateDTO implements BaseDTO {
    private String authId;
    private List<String> followingsId;
}
