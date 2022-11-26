package org.khasanof.auth_service.dto.employment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.entity.location.LocationEntity;
import org.khasanof.auth_service.enums.employment.EmploymentTypeEnum;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentGetDTO extends GenericDTO {
    private String id;
    private String company;
    private String position;
    private Date startYear;
    private Date endYear;
    private EmploymentTypeEnum type;
}
