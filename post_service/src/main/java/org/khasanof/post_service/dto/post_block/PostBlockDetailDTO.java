package org.khasanof.post_service.dto.post_block;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.entity.post.PostEntity;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostBlockDetailDTO extends GenericDTO {
    private PostEntity postId;
    private String blockedCode;
    private boolean IsWillNotOpen;
    private Instant duration;
}
