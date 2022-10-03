package org.khasanof.post_service.dto.post_like;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostLikeGetDTO extends GenericDTO {
    private String likePostId;
    private Integer likesCount;
    private Instant createdAt;
}
