package org.khasanof.auth_service.enums.auth_invite;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthInviteStatusEnum {
    ACCEPT("ACCEPT"),
    NO_ACCEPT("NO_ACCEPT"),
    PENDING("PENDING");
    private final String value;
}
