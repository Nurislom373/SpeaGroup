package org.khasanof.post_service.dto.auth_user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.post_service.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserUpdateDTO extends GenericDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String description;
    private String username;
    private String language;
}
