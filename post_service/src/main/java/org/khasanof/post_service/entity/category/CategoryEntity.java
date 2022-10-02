package org.khasanof.post_service.entity.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.post_service.entity.Auditable;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "category")
public class CategoryEntity extends Auditable {
    private String name;
    private String code;
}
