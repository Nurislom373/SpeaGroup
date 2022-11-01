package org.khasanof.auth_service.dto.location;

import lombok.*;
import org.khasanof.auth_service.annotation.MongoIdConstraint;
import org.khasanof.auth_service.dto.GenericDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LocationUpdateDTO extends GenericDTO {
    @NotBlank
    @MongoIdConstraint
    private String infoId;
    @NotBlank
    @Size(min = 2, max = 120)
    private String country;
    @NotBlank
    @Size(min = 2, max = 120)
    private String city;
}
