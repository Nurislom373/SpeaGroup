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
public class AuthUserDetailDTO extends GenericDTO {
    // TODO ichida full variablelari bo'ladi!
    private String firstName;
    private String lastName;
    private String email;
    private String description;
    private String username;
    private String password;
    private String language;
    private String status;
    private String lastLoginAt;
    private String imagePath;
    private boolean isVerified;
    private boolean isLogout;
    private String logoutTime;
}
