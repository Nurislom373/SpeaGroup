package org.khasanof.post_service.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.post_service.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRemoveLikeDTO extends GenericDTO {
    private String postId;
    private String commentId;
    private String likeId;
}
