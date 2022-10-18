package org.khasanof.auth_service.enums.auth_invite;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum AuthInviteStatusEnum {
    ACCEPT("ACCEPT"),
    NO_ACCEPT("NO_ACCEPT"),
    PENDING("PENDING");
    private final String value;

    public static boolean hasStatusEnum(String var) {
        return Arrays.stream(values())
                .anyMatch(
                        obj -> obj.value.equalsIgnoreCase(var)
                );
    }
}
