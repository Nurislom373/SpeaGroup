package org.khasanof.question_service.dto.auth_user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.question_service.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserGetDTO extends GenericDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String description;
    private String username;
    private String language;
    private String lastLoginAt;
    private String imagePath;
    private boolean isVerified;
    private boolean isLogout;
    private String logoutTime;
}
