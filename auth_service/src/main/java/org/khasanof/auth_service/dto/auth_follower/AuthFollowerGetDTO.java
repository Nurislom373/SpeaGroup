package org.khasanof.auth_service.dto.auth_follower;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.GenericDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthFollowerGetDTO extends GenericDTO {
    private String userId;
    private List<String> followersId;
}
