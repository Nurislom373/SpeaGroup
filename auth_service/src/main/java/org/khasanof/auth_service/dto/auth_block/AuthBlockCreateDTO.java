package org.khasanof.auth_service.dto.auth_block;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.annotation.MongoIdConstraint;
import org.khasanof.auth_service.dto.BaseDTO;
import org.khasanof.auth_service.entity.blocked_for.BlockedForEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthBlockCreateDTO implements BaseDTO {
    @NotBlank
    @MongoIdConstraint
    private String authId;
    @NotBlank
    @MongoIdConstraint
    private String blockedForId;
}
