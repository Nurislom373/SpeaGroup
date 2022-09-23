package org.khasanof.auth_service.dto.auth_block;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthBlockUpdateDTO extends GenericDTO {
    private String authId;
    private Integer durationTime;
    private String blockedForId;
}