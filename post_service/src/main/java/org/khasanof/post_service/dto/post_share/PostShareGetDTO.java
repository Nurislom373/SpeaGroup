package org.khasanof.post_service.dto.post_share;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostShareGetDTO extends GenericDTO {
    private String sharePostId;
    private Integer sharesCount;
}
