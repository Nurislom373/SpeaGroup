package org.khasanof.auth_service.dto.employment;

import lombok.*;
import org.khasanof.auth_service.annotation.DateConstraint;
import org.khasanof.auth_service.annotation.MongoIdConstraint;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.enums.employment.EmploymentTypeEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmploymentUpdateDTO extends GenericDTO {
    @NotBlank
    @MongoIdConstraint
    private String infoId;
    @NotBlank
    private String employmentId;
    @NotBlank
    @Size(min = 2, max = 120)
    private String company;
    @NotBlank
    @Size(min = 2, max = 120)
    private String position;
    @NotBlank
    @DateConstraint
    private String startYearStr;
    @NotBlank
    @DateConstraint
    private String endYearStr;
    @NotNull
    private EmploymentTypeEnum type;
}
