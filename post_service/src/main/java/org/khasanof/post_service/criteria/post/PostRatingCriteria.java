package org.khasanof.post_service.criteria.post;

import lombok.Getter;
import lombok.Setter;
import org.khasanof.post_service.criteria.GenericCriteria;
import org.khasanof.post_service.enums.rating.RatingTypeEnum;
import org.springdoc.api.annotations.ParameterObject;

@Getter
@Setter
@ParameterObject
public class PostRatingCriteria extends GenericCriteria {
    private RatingTypeEnum typeEnum;
}
