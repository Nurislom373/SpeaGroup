package org.khasanof.post_service.dto.count;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GenericCount {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private Integer count;
}
