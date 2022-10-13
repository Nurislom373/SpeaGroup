package org.khasanof.post_service.dto.post;

import lombok.*;
import org.khasanof.post_service.dto.BaseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostCreateDTO implements BaseDTO {
    @NotBlank
    @Size(min = 5, max = 50)
    private String postUserId;
    @NotBlank
    @Size(min = 5, max = 250)
    private String title;
    @NotBlank
    @Size(min = 50, max = 2500)
    private String description;
    @NotNull
    private List<String> mediaPaths;
    @NotBlank
    private String visibility;
    @NotNull
    private List<String> categoriesIds;
}
