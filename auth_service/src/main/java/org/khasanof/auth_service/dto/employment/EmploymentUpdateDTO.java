package org.khasanof.auth_service.dto.employment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.enums.employment.EmploymentTypeEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentUpdateDTO extends GenericDTO {
    @NotBlank
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
    private String startYearStr;
    @NotBlank
    private String endYearStr;
    @NotNull
    private EmploymentTypeEnum type;
}
