package org.khasanof.post_service.dto.post_block;

import lombok.*;
import org.khasanof.post_service.dto.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostBlockCreateDTO implements BaseDTO {
    private String blockedPostId;
    private String blockedCode;
    private boolean IsWillNotOpen;
}
