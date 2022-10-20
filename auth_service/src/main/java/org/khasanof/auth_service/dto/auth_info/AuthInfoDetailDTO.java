package org.khasanof.auth_service.dto.auth_info;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.dto.category.CategoryGetDTO;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.entity.education.EducationEntity;
import org.khasanof.auth_service.entity.employment.EmploymentEntity;
import org.khasanof.auth_service.entity.location.LocationEntity;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthInfoDetailDTO extends GenericDTO {
    private AuthUserEntity user;
    private LocationEntity location;
    private List<EducationEntity> educations;
    private List<EmploymentEntity> employments;
    private Short age;
    private Date bornYear;
    private String phoneNumber;
    private List<String> interests;
}
