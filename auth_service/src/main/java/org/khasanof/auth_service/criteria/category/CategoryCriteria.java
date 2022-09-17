package org.khasanof.auth_service.criteria.category;

import lombok.Getter;
import lombok.Setter;
import org.khasanof.auth_service.criteria.GenericCriteria;
import org.khasanof.auth_service.enums.category.CategoryFieldsEnum;
import org.springdoc.api.annotations.ParameterObject;

@Getter
@Setter
@ParameterObject
public class CategoryCriteria extends GenericCriteria {
    private CategoryFieldsEnum fieldsEnum;
}
