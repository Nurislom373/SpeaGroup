package org.khasanof.post_service.dto.post_view;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostViewGetDTO extends GenericDTO {
    private String viewPostId;
    private Integer viewsCount;
    private Instant createdAt;

}
