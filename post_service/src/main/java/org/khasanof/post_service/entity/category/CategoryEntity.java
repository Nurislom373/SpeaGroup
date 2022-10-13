package org.khasanof.post_service.entity.category;

import lombok.*;
import org.khasanof.post_service.entity.Auditable;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "category")
public class CategoryEntity extends Auditable {
    private String name;
    private String code;
}
