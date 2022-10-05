package org.khasanof.post_service.enums.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostFieldsEnum {
    TITLE("TITLE"),
    DESCRIPTION("DESCRIPTION"),
    STATUS("STATUS"),
    VISIBILITY("VISIBILITY");
    private final String value;
}
