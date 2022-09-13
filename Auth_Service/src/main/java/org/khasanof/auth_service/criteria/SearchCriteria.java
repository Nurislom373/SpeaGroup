package org.khasanof.auth_service.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SearchCriteria extends GenericCriteria {
    protected String key;
    protected String operation;
    protected String value;
}
