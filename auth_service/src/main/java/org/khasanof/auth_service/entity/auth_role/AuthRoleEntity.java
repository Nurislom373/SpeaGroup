package org.khasanof.auth_service.entity.auth_role;

import lombok.*;
import org.khasanof.auth_service.entity.Auditable;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "auth_role")
@ToString
public class AuthRoleEntity extends Auditable {
    @DocumentReference
    @Field(name = "user_id")
    private AuthUserEntity userId;
    private String role;
}
