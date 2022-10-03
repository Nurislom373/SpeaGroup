package org.khasanof.post_service.dto.post_rating;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostRatingRemoveSaveDTO {
    private String ratingPostId;
    private String userId;
}
