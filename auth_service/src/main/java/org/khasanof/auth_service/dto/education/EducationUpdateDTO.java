package org.khasanof.auth_service.dto.education;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EducationUpdateDTO extends GenericDTO {
    private String infoId;
    private String education;
    private String startYearStr;
    private String endYearStr;
    private String primaryMajor;
    private String secondaryMajor;
}
