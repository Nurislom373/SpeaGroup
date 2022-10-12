package org.khasanof.post_service.criteria.post;

import lombok.Getter;
import lombok.Setter;
import org.khasanof.post_service.criteria.GenericCriteria;
import org.springdoc.api.annotations.ParameterObject;

@Getter
@Setter
@ParameterObject
public class PostCatCriteria extends GenericCriteria {
    private String categoryId;
}
