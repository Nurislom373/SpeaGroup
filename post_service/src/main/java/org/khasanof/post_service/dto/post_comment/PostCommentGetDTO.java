package org.khasanof.post_service.dto.post_comment;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostCommentGetDTO extends GenericDTO {
    private String postId;
    private Integer commentsCount;
    private String lastUpdateType;
}
