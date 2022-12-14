package org.khasanof.auth_service.entity.auth_token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.entity.Auditable;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.enums.auth_token.AuthTokenType;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "auth_token")
public class AuthTokenEntity extends Auditable {
    @Field(name = "user_id")
    @DocumentReference
    private AuthUserEntity userId;
    private AuthTokenType type;
    private String token;
    private Instant duration;
    private boolean isDead;

    public AuthTokenEntity(AuthUserEntity userId, AuthTokenType type, String token, Instant duration) {
        this.userId = userId;
        this.type = type;
        this.token = token;
        this.duration = duration;
    }
}
