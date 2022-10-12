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
    private Integer likesCount;
    private Integer viewsCount;
    private Integer savedCount;
    private Integer sharesCount;
    private String ratingType;
}
