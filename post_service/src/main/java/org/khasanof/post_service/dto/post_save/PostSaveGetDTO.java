package org.khasanof.post_service.dto.post_save;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostSaveGetDTO extends GenericDTO {
    private String savePostId;
    private Integer saveCount;
    private Instant createdAt;
}
