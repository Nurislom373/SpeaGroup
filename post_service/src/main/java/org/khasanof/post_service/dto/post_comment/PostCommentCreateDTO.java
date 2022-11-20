package org.khasanof.post_service.dto.post_comment;

import lombok.*;
import org.khasanof.post_service.dto.BaseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostCommentCreateDTO implements BaseDTO {
    @NotBlank
    private String commentPostId;
    @NotBlank
    private String userId;
    private String replyId;
    @NotBlank
    @Size(min = 5, max = 2500)
    private String message;
}
