package org.khasanof.auth_service.dto.auth_user;

import lombok.*;
import org.khasanof.auth_service.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserDetailDTO extends GenericDTO {
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
