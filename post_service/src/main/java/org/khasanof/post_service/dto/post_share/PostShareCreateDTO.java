package org.khasanof.post_service.dto.post_share;

import lombok.*;
import org.khasanof.post_service.dto.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostShareCreateDTO implements BaseDTO {
    private String sharePostId;
    private String userId;
    private String type;
}
