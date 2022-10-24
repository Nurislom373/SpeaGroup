package org.khasanof.auth_service.dto.auth;

import lombok.*;
import org.khasanof.auth_service.annotation.MongoIdConstraint;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthChangeImagePathDTO {
    @NotBlank
    @MongoIdConstraint
    private String userId;
    @NotBlank
    private String newImagePath;
}
