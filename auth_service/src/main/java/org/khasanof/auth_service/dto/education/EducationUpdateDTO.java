package org.khasanof.auth_service.dto.education;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.annotation.DateConstraint;
import org.khasanof.auth_service.annotation.MongoIdConstraint;
import org.khasanof.auth_service.dto.GenericDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EducationUpdateDTO extends GenericDTO {
    @NotBlank
    @MongoIdConstraint
    private String infoId;
    @NotBlank
    private String educationId;
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
