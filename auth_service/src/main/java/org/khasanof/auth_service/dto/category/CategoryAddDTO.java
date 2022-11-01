package org.khasanof.auth_service.dto.category;

import lombok.*;
import org.khasanof.auth_service.annotation.MongoIdConstraint;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryAddDTO {
    @NotBlank
    @MongoIdConstraint
    private String infoId;
    @NotBlank
    @MongoIdConstraint
    private String categoryId;
}
