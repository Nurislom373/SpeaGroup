package org.khasanof.post_service.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.post_service.dto.GenericDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryUpdateDTO extends GenericDTO {
    @NotBlank
    @Size(min = 2, max = 50, message = "code min size 2 max size 50 should in the area")
    private String code;
    @NotBlank
    @Size(min = 2, max = 100, message = "name min size 2 max size 100 should in the area")
    private String name;
}
