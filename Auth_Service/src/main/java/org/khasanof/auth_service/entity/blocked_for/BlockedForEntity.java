package org.khasanof.auth_service.entity.blocked_for;

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
@Document(collection = "blocked_for")
public class BlockedForEntity extends Auditable {
    private String code;
    private String name;
    private Integer time;
}
