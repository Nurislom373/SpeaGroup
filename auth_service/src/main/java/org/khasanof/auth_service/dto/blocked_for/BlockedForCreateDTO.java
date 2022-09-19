package org.khasanof.auth_service.dto.blocked_for;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.BaseDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlockedForCreateDTO implements BaseDTO {
    @NotNull
    @Size(min = 3, max = 120, message = "BlockedForCreateDTO name field min size 3 max size 120")
    private String name;
    @NotNull
    @Size(min = 3, max = 120, message = "BlockedForCreateDTO code field min size 3 max size 120")
    private String code;
    @NotNull
    private Integer time;
}
