package org.khasanof.post_service.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.post_service.dto.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateDTO implements BaseDTO {
    private String userId;
    private String replyId;
    private String message;
}
