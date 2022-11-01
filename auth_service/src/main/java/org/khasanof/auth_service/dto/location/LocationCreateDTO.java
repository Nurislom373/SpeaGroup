package org.khasanof.auth_service.dto.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.annotation.MongoIdConstraint;
import org.khasanof.auth_service.dto.BaseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationCreateDTO implements BaseDTO {
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
