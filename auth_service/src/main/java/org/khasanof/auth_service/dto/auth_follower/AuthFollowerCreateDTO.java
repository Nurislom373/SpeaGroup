package org.khasanof.auth_service.dto.auth_follower;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.annotation.MongoIdConstraint;
import org.khasanof.auth_service.dto.BaseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthFollowerCreateDTO implements BaseDTO {
    @NotBlank
    @MongoIdConstraint
    private String authId;
    @NotNull
    private List<String> followerId;
}
