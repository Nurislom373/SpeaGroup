package org.khasanof.auth_service.dto.auth_user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.GenericDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserUpdateDTO extends GenericDTO {
    @NotNull
    @NotBlank
    @Size(min = 3, max = 120)
    private String firstName;
    @NotNull
    @NotBlank
    @Size(min = 3, max = 120)
    private String lastName;
    @NotNull
    @NotBlank
    @Size(min = 5, max = 250)
    private String email;
    private String description;
    @NotNull
    @NotBlank
    @Size(min = 4, max = 50)
    private String username;
    @NotNull
    @NotBlank
    @Size(min = 2, max = 2)
    private String language;
}
