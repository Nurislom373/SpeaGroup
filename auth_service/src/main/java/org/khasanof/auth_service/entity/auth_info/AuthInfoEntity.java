package org.khasanof.auth_service.entity.auth_info;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.entity.Auditable;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.entity.education.EducationEntity;
import org.khasanof.auth_service.entity.employment.EmploymentEntity;
import org.khasanof.auth_service.entity.location.LocationEntity;
import org.khasanof.auth_service.enums.auth_info.AuthInfoVisibilityEnum;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "auth_info")
public class AuthInfoEntity extends Auditable {
    @DocumentReference
    @Field(name = "user_id")
    private AuthUserEntity userId;
    private LocationEntity location;
    private List<EducationEntity> educations;
    private List<EmploymentEntity> employments;
    @Field(name = "born_year")
    private String bornYear;
    @Field(name = "phone_number")
    private String phoneNumber;
    private AuthInfoVisibilityEnum visibility;
    private List<String> interests;
}
