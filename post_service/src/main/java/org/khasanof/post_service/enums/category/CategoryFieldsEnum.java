package org.khasanof.post_service.enums.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CategoryFieldsEnum {
    NAME("name"),
    CODE("code");
    private final String value;
}
