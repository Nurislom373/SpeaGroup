package org.khasanof.auth_service.dto.blocked_for;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlockedForGetDTO extends GenericDTO {
    private String name;
    private String code;
    private Integer time;
}
