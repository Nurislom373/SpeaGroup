package org.khasanof.post_service.dto.post;

import lombok.*;
import org.khasanof.post_service.dto.BaseDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostCreateDTO implements BaseDTO {
    private String postUserId;
    private String title;
    private String description;
    private List<String> mediaPaths;
    private String visibility;
}
