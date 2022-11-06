package org.khasanof.auth_service.dto.education;

import lombok.*;
import org.khasanof.auth_service.annotation.DateConstraint;
import org.khasanof.auth_service.annotation.MongoIdConstraint;
import org.khasanof.auth_service.dto.BaseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EducationCreateDTO implements BaseDTO {
    @NotBlank
    @MongoIdConstraint
    private String infoId;
    @NotBlank
    @Size(min = 2, max = 250, message = "education must be not null")
    private String education;
    @NotBlank
    @DateConstraint
    private String startYearStr;
    @NotBlank
    @DateConstraint
    private String endYearStr;
    @NotBlank
    @Size(min = 5, max = 250, message = "primaryMajor must be not null")
    private String primaryMajor;
    @NotBlank
    @Size(min = 5, max = 250, message = "secondaryMajor must be not null")
    private String secondaryMajor;
}
