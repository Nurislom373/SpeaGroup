package org.khasanof.post_service.dto.post;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostUpdateDTO extends GenericDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String visibility;
}
