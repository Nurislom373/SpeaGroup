package org.khasanof.auth_service.dto.employment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.entity.location.LocationEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentUpdateDTO extends GenericDTO {
    private String infoId;
    private String company;
    private String position;
    private String startYearStr;
    private String endYearStr;
    private String type;
    private LocationEntity location;
}
