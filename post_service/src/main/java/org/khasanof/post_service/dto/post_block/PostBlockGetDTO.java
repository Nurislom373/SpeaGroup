package org.khasanof.post_service.dto.post_block;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostBlockGetDTO extends GenericDTO {
    private String blockedPostId;
    private String blockedCode;
    private boolean IsWillNotOpen;
}
