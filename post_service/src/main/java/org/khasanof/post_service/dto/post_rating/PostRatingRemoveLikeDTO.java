package org.khasanof.post_service.dto.post_rating;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostRatingRemoveLikeDTO extends GenericDTO {
    private String ratingPostId;
    private String userId;
}
