package org.khasanof.post_service.dto.auth_user;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordChangesDTO {
    @NotBlank(message = "id can not be blank or null")
    private String id;
    @NotBlank(message = "old password can not be blank or null")
    private String oldPassword;
    @NotBlank(message = "new password can not be blank or null")
    private String newPassword;
    @NotBlank(message = "confirm password can not be blank or null")
    private String confirmPassword;
}
