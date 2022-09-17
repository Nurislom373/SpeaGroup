package org.khasanof.auth_service.entity.auth_user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.entity.Auditable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "auth_user")
public class AuthUserEntity extends Auditable {
    @Field(name = "first_name")
    private String firstName;
    @Field(name = "last_name")
    private String lastName;
    private String email;
    private String description;
    private String username;
    private String password;
    private String language;
    private String status;
    @Field(name = "last_loin_at")
    private Instant lastLoginAt;
    @Field(name = "image_path")
    private String imagePath;
    private boolean isVerified;
    private boolean isLogout;
    @Field(name = "logout_time")
    private Instant logoutTime;
}
