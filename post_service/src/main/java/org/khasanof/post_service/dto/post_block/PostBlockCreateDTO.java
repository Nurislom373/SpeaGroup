package org.khasanof.post_service.dto.post_block;

import lombok.*;
import org.khasanof.post_service.dto.BaseDTO;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostBlockCreateDTO implements BaseDTO {
    @NotBlank
    private String blockedPostId;
    @NotBlank
    private String blockedCode;
    private boolean IsWillNotOpen;
}
