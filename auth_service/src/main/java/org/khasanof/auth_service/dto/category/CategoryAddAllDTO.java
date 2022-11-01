package org.khasanof.auth_service.dto.category;

import lombok.*;
import org.khasanof.auth_service.annotation.MongoIdConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryAddAllDTO {
    @NotBlank
    @MongoIdConstraint
    private String infoId;
    @NotNull
    private List<String> categories;
}
