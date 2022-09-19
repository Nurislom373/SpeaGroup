package org.khasanof.auth_service.dto.auth_follower;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.BaseDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthFollowerCreateDTO implements BaseDTO {
    private String authId;
    private List<String> followerId;
}
