package org.khasanof.auth_service.dto.auth_info;

import lombok.*;
import org.khasanof.auth_service.dto.GenericDTO;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthInfoUpdateDTO extends GenericDTO {

    private String bornYear;
    private String phoneNumber;

    public AuthInfoUpdateDTO(String id, String bornYear, String phoneNumber) {
        super(id);
        this.bornYear = bornYear;
        this.phoneNumber = phoneNumber;
    }
}
