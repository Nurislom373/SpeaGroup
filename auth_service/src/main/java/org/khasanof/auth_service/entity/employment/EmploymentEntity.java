package org.khasanof.auth_service.entity.employment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.entity.Auditable;
import org.khasanof.auth_service.entity.BaseEntity;
import org.khasanof.auth_service.entity.location.LocationEntity;
import org.khasanof.auth_service.enums.employment.EmploymentTypeEnum;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentEntity implements BaseEntity {
    private String id;
    private String company;
    private String position;
    private Date startYear;
    private Date endYear;
    private EmploymentTypeEnum type;

    public EmploymentEntity(String company, String position, Date startYear, Date endYear, EmploymentTypeEnum type) {
        this.id = String.valueOf(System.currentTimeMillis());
        this.company = company;
        this.position = position;
        this.startYear = startYear;
        this.endYear = endYear;
        this.type = type;
    }
}
