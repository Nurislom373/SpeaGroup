package org.khasanof.post_service.dto.post;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.entity.auth_user.AuthUserEntity;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostDetailDTO extends GenericDTO {
    private AuthUserEntity userId;
    private String title;
    private String description;
    private String status;
    private List<String> mediaPaths;
    private Integer likesCount;
    private Integer viewsCount;
    private Integer commentsCount;
    private Integer savesCount;
    private String visibility;
    private Instant createdAt;
    private Instant updatedAt;
}
