package org.khasanof.auth_service.enums.auth_info;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthInfoFieldsEnum {
    BORN_YEAR("born_year"),
    PHONE_NUMBER("phone_number");
    private final String value;
}
