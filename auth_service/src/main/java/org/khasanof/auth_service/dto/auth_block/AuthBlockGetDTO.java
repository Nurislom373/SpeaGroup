package org.khasanof.auth_service.dto.auth_block;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.GenericDTO;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthBlockGetDTO extends GenericDTO {
    private String authId;
    private Instant duration;
    private String blockedForCode;
}
