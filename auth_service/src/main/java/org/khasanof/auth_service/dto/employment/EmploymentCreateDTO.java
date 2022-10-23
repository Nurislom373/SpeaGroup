package org.khasanof.auth_service.dto.employment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.annotation.DateConstraint;
import org.khasanof.auth_service.annotation.MongoIdConstraint;
import org.khasanof.auth_service.dto.BaseDTO;
import org.khasanof.auth_service.entity.location.LocationEntity;
import org.khasanof.auth_service.enums.employment.EmploymentTypeEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentCreateDTO implements BaseDTO {
    @NotBlank
    private String infoId;
    @NotBlank
    @Size(min = 2, max = 120, message = "company must be not null")
    private String company;
    @NotNull
    @NotBlank
    @Size(min = 2, max = 120, message = "position must be not null")
    private String position;
    @NotBlank
    private String startYearStr;
    @NotBlank
    private String endYearStr;
    @NotNull
    private EmploymentTypeEnum type;
}
