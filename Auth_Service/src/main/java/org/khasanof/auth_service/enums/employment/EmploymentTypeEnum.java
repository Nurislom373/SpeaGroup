package org.khasanof.auth_service.enums.employment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmploymentTypeEnum {
    INTERNSHIP,
    FREELANCE,
    CONTRACT,
    SELF_EMPLOYED,
    SEASONAL,
    APPRENTICESHIP,
    FULL_TIME,
    PART_TIME;
}
