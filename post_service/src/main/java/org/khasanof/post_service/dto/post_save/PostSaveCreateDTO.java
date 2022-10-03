package org.khasanof.post_service.dto.post_save;

import lombok.*;
import org.khasanof.post_service.dto.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostSaveCreateDTO implements BaseDTO {
    private String savePostId;
    private String userId;
}
