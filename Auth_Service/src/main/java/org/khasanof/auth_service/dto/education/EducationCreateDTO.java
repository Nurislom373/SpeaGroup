package org.khasanof.auth_service.dto.education;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EducationCreateDTO implements BaseDTO {
    private String infoId;
    private String education;
    private String startYearStr;
    private String endYearStr;
    private String primaryMajor;
    private String secondaryMajor;
}
