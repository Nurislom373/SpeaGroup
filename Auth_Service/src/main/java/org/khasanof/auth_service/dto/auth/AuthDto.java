package org.khasanof.auth_service.dto.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthDto {
    private String phoneNumber;
    private String password;
}
