package org.khasanof.post_service.dto.post_rating;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.entity.like.LikeEntity;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.entity.view.ViewEntity;

import java.time.Instant;
import java.util.LinkedList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostRatingDetailDTO extends GenericDTO {
    private PostEntity postId;
    private Integer savedCount;
    private Integer likesCount;
    private Integer viewsCount;
    private Integer sharesCount;
    private String ratingType;
    private Instant createdAt;
    private Instant updatedAt;
    private String lastUpdateType;
}
