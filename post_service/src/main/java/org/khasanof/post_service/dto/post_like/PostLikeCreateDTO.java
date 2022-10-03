package org.khasanof.post_service.dto.post_like;

import lombok.*;
import org.khasanof.post_service.dto.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostLikeCreateDTO implements BaseDTO {
    private String likePostId;
    private String userId;
    private String type;
}
