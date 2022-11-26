package org.khasanof.auth_service.dto.blocked_for;

import lombok.*;
import org.khasanof.auth_service.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BlockedForGetDTO extends GenericDTO {
    private String name;
    private String code;
    private Integer time;
}
