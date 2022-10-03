package org.khasanof.post_service.dto.post_view;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.entity.view.ViewEntity;

import java.time.Instant;
import java.util.LinkedList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostViewDetailDTO extends GenericDTO {
    private PostEntity postId;
    private LinkedList<ViewEntity> views;
    private Instant createdAt;
    private Instant updatedAt;
}
