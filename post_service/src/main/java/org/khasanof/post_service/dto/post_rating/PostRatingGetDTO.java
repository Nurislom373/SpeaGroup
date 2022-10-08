package org.khasanof.post_service.dto.post_rating;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostRatingGetDTO extends GenericDTO {
    private String ratingPostId;
    private Long likeCount;
    private Long viewsCount;
    private Long savedCount;
    private String ratingType;
}
