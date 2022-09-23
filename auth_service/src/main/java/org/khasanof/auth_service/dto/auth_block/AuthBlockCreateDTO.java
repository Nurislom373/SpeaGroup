package org.khasanof.auth_service.dto.auth_block;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.BaseDTO;
import org.khasanof.auth_service.entity.blocked_for.BlockedForEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthBlockCreateDTO implements BaseDTO {
    private String authId;
    private Integer durationTime;
    private BlockedForEntity blockedFor;
}
