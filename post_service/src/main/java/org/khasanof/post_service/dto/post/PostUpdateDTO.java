package org.khasanof.post_service.dto.post;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.enums.post.PostVisibilityEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotNull
    private PostVisibilityEnum visibility;
}
