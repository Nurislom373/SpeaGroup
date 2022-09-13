package org.khasanof.auth_service.dto.auth_user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthDTO {
    private String phoneNumber;
    private String password;
}
