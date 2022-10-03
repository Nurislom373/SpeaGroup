package org.khasanof.post_service.dto.post_like;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.post_service.dto.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostLikeDeleteDTO implements BaseDTO {
    private String likePostId;
    private String userId;
}
