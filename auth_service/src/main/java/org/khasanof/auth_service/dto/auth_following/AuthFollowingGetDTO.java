package org.khasanof.auth_service.dto.auth_following;

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
public class AuthFollowingGetDTO extends GenericDTO {
    private String authId;
    private List<String> followingsId;
}
