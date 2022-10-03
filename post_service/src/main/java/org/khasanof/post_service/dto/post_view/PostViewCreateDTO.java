package org.khasanof.post_service.dto.post_view;

import lombok.*;
import org.khasanof.post_service.dto.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostViewCreateDTO implements BaseDTO {
    private String viewPostId;
    private String userId;
}
