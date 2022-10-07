package org.khasanof.post_service.dto.post_comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.post_service.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentRemoveLikeDTO extends GenericDTO {
    private String commentId;
    private String userId;
}
