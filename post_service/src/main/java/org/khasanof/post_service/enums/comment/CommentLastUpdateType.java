package org.khasanof.post_service.enums.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommentLastUpdateType {
    ADD_LIKE("ADD_LIKE"),
    REMOVE_LIKE("REMOVE_LIKE"),
    ADD_COMMENT("ADD_COMMENT"),
    REMOVE_COMMENT("REMOVE_COMMENT");
    private final String value;
}
