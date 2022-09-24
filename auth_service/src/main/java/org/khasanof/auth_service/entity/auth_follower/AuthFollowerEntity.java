package org.khasanof.auth_service.entity.auth_follower;

import lombok.*;
import org.khasanof.auth_service.entity.Auditable;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "auth_follower")
@Builder
public class AuthFollowerEntity extends Auditable {
    @DocumentReference
    @Field(name = "user_id")
    private AuthUserEntity userId;
    @DocumentReference
    private List<AuthUserEntity> followers;
}
