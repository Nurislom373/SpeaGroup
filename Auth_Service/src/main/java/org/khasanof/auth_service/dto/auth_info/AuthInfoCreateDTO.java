package org.khasanof.auth_service.dto.auth_info;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.BaseDTO;
import org.khasanof.auth_service.entity.education.EducationEntity;
import org.khasanof.auth_service.entity.employment.EmploymentEntity;
import org.khasanof.auth_service.entity.location.LocationEntity;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthInfoCreateDTO implements BaseDTO {
    private String authid;
    private LocationEntity location;
    private List<EducationEntity> educations;
    private List<EmploymentEntity> employments;
    private String bornYearStr;
    private String phoneNumber;
    private List<String> interestsId;
}
