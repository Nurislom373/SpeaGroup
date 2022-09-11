package org.khasanof.auth_service.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.GenericDTO;
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
    private String imagePath;

}
