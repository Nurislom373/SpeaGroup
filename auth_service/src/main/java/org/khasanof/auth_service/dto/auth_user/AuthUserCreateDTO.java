package org.khasanof.auth_service.dto.auth_user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.BaseDTO;
import org.khasanof.auth_service.enums.language.LanguageEnums;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserCreateDTO implements BaseDTO {
    @NotBlank(message = "firstName is mandatory")
    @Size(min = 3, max = 120)
    private String firstName;
    @NotBlank(message = "lastName is mandatory")
    @Size(min = 3, max = 120)
    private String lastName;
    @NotBlank(message = "email is mandatory")
    @Email
    @Size(min = 5, max = 250)
    private String email;
    private String description;
    @NotBlank(message = "username is mandatory")
    @Size(min = 5, max = 50)
    private String username;
    @NotBlank(message = "password is mandatory")
    @Size(min = 8, max = 120)
    private String password;
    @NotBlank(message = "language is mandatory")
    @Size(min = 2, max = 2)
    private LanguageEnums language;
    @NotBlank(message = "imagePath is mandatory")
    private String imagePath;
    private List<String> categoryIds;
}
