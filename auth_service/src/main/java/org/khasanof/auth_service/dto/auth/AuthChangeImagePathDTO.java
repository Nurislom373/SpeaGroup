package org.khasanof.auth_service.dto.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthChangeImagePathDTO {
    private String userId;
    private String newImagePath;
}
