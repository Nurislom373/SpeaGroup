package org.khasanof.post_service.dto.post_block;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostBlockUpdateDTO extends GenericDTO {
    @NotBlank
    private String blockedCode;
    private boolean IsWillNotOpen;
}
