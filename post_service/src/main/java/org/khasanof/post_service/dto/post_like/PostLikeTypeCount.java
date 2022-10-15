package org.khasanof.post_service.dto.post_like;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostLikeTypeCount extends GenericDTO {
    private String postId;
    private Integer count;
    private String type;
}
