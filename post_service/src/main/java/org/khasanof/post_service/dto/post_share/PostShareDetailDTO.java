package org.khasanof.post_service.dto.post_share;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.entity.share.ShareEntity;

import java.time.Instant;
import java.util.LinkedList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostShareDetailDTO extends GenericDTO {
    private PostEntity postId;
    private LinkedList<ShareEntity> shares;
    private Instant createdAt;
    private Instant updatedAt;
}
