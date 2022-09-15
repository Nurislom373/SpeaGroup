package org.khasanof.auth_service.dto.employment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.BaseDTO;
import org.khasanof.auth_service.entity.location.LocationEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentCreateDTO implements BaseDTO {
    private String company;
    private String position;
    private String startYearStr;
    private String endYearStr;
    private String type;
    private LocationEntity location;
}
