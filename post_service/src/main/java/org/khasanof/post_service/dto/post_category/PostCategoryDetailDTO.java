package org.khasanof.post_service.dto.post_category;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.entity.category.CategoryEntity;
import org.khasanof.post_service.entity.post.PostEntity;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostCategoryDetailDTO extends GenericDTO {
    private PostEntity postId;
    private List<CategoryEntity> categories;
    private Instant updatedAt;
    private Instant createdAt;
}
