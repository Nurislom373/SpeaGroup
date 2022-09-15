package org.khasanof.auth_service.entity.employment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.entity.Auditable;
import org.khasanof.auth_service.entity.location.LocationEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "employment")
public class EmploymentEntity extends Auditable {
    private String company;
    private String position;
    private Date startYear;
    private Date endYear;
    private String type;
    private LocationEntity location;
}
