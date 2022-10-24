package org.khasanof.auth_service.entity.education;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.entity.Auditable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EducationEntity {
    private String id;
    private String education;
    private Date startYear;
    private Date endYear;
    private String primaryMajor;
    private String secondaryMajor;

    public EducationEntity(String education, Date startYear, Date endYear, String primaryMajor, String secondaryMajor) {
        this.id = String.valueOf(System.currentTimeMillis());
        this.education = education;
        this.startYear = startYear;
        this.endYear = endYear;
        this.primaryMajor = primaryMajor;
        this.secondaryMajor = secondaryMajor;
    }
}
