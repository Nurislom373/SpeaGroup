package org.khasanof.upload_service.upload;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CloudinaryFieldsEnum {
    SIZE("size"),
    WIDTH("width"),
    HEIGHT("height"),
    NAME("name");
    private final String value;
}
