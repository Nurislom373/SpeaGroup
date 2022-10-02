package org.khasanof.post_service.enums.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum CommentRemoveRequestRoleEnum {
    USER("USER"),
    OWNER("OWNER"),
    ADMIN("ADMIN");
    private final String value;

    public static boolean hasBlockedFor(String var) {
        return Arrays.stream(values())
                .anyMatch(
                        obj -> obj.value.equals(var)
                );
    }
}
