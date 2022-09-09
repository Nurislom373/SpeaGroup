package org.khasanof.auth_service.entity.authBlock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.auth_service.entity.Auditable;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "auth_block")
public class AuthBlockEntity extends Auditable {
    private String userId;
}
