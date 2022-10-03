package org.khasanof.post_service.dto.post_save;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.entity.save.SaveEntity;

import java.time.Instant;
import java.util.LinkedList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostSaveDetailDTO extends GenericDTO {
    private PostEntity postId;
    private LinkedList<SaveEntity> saves;
    private Instant createdAt;
    private Instant updatedAt;
}
