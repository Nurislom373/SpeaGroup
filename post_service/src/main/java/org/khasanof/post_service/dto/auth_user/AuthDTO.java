package org.khasanof.post_service.dto.auth_user;

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
