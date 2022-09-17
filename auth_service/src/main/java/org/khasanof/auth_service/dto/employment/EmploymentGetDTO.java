package org.khasanof.auth_service.dto.employment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.entity.location.LocationEntity;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentGetDTO extends GenericDTO {
    private String company;
    private String position;
    private Date startYear;
    private Date endYear;
    private String type;
    private LocationEntity location;
}
