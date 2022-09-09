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
    private String key;
    private String operation;
    private String value;
}
