package org.khasanof.auth_service.dto.education;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.GenericDTO;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EducationGetDTO extends GenericDTO {
    private String education;
    private Date startYear;
    private Date endYear;
    private String primaryMajor;
    private String secondaryMajor;
}
