package org.khasanof.auth_service.dto.auth_user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.enums.language.LanguageEnums;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserUpdateDTO extends GenericDTO {
    @NotBlank
    @Size(min = 3, max = 120)
    private String firstName;
    @NotBlank
    @Size(min = 3, max = 120)
    private String lastName;
    @NotBlank
    @Size(min = 5, max = 250)
    private String email;
    private String description;
    @NotBlank
    @Size(min = 4, max = 50)
    private String username;
    @NotNull
    private LanguageEnums language;

    public AuthUserUpdateDTO(String id, String firstName, String lastName, String email, String description, String username, LanguageEnums language) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.description = description;
        this.username = username;
        this.language = language;
    }
}
