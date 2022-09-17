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
@Document(collection = "education")
public class EducationEntity extends Auditable {
    private String education;
    @Field(name = "start_year")
    private Date startYear;
    @Field(name = "end_year")
    private Date endYear;
    @Field(name = "primary_major")
    private String primaryMajor;
    @Field(name = "secondary_major")
    private String secondaryMajor;
}
