package org.khasanof.post_service.dto.post_rating;

import lombok.*;
import org.khasanof.post_service.dto.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostRatingAddLikeDTO implements BaseDTO {
    private String ratingPostId;
    private String userId;
    private String type;
}
