package org.khasanof.post_service.dto.auth_following;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthFollowingGetDTO extends GenericDTO {
    private String authId;
    private List<String> followingsId;
}
