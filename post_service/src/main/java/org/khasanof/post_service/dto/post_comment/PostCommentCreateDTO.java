package org.khasanof.post_service.dto.post_comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.post_service.dto.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentCreateDTO implements BaseDTO {
    private String commentPostId;
    private String userId;
    private String replyId;
    private String message;
}
