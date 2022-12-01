package org.khasanof.post_service.dto.post;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.enums.post.PostVisibilityEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostUpdateDTO extends GenericDTO {
    @NotBlank
    @Size(min = 5, max = 250)
    private String title;
    @NotBlank
    @Size(min = 50, max = 2500)
    private String description;
    @NotNull
    private PostVisibilityEnum visibility;

    public PostUpdateDTO(String id, String title, String description, PostVisibilityEnum visibility) {
        super(id);
        this.title = title;
        this.description = description;
        this.visibility = visibility;
    }
}
