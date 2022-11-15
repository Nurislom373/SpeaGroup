package org.khasanof.post_service.dto.post;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostGetDTO extends GenericDTO {
    private String userId;
    private String title;
    private String description;
    private List<String> mediaPaths;
    private String visibility;
}
