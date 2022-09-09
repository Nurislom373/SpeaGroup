package org.khasanof.auth_service.entity.authRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.entity.Auditable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "auth_role")
public class AuthRoleEntity extends Auditable {
    @Field(name = "user_id")
    private String userId;
    private String role;
}
