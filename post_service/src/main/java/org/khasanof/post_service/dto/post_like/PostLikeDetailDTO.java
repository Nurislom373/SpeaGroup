package org.khasanof.post_service.dto.post_like;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.entity.like.LikeEntity;
import org.khasanof.post_service.entity.post.PostEntity;

import java.time.Instant;
import java.util.LinkedList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostLikeDetailDTO extends GenericDTO {
    private PostEntity postId;
    private LinkedList<LikeEntity> likes;
    private Instant createdAt;
    private Instant updatedAt;
}
