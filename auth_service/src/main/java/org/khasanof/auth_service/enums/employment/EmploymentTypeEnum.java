package org.khasanof.auth_service.enums.employment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmploymentTypeEnum {
    INTERNSHIP("INTERNSHIP"),
    FREELANCE("FREELANCE"),
    CONTRACT("CONTRACT"),
    SELF_EMPLOYED("SELF_EMPLOYED"),
    SEASONAL("SEASONAL"),
    APPRENTICESHIP("APPRENTICESHIP"),
    FULL_TIME("FULL_TIME"),
    PART_TIME("PART_TIME");
    private final String value;
}
