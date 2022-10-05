package org.khasanof.post_service.dto.auth_user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.post_service.dto.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserCreateDTO implements BaseDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String description;
    private String username;
    private String password;
    private String language;
    private String imagePath;
}
