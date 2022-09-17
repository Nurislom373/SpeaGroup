package org.khasanof.auth_service.dto.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationUpdateDTO extends GenericDTO {
    private String infoId;
    private String country;
    private String city;
}
