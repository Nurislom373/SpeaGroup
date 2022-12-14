package org.khasanof.auth_service.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BetweenCriteria extends GenericCriteria {
    protected String key;
    protected Integer from;
    protected Integer to;
}
