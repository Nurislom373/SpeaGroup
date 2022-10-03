package org.khasanof.post_service.dto.post_category;

import lombok.*;
import org.khasanof.post_service.dto.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostCategoryAddDTO implements BaseDTO {
    private String postId;
    private String categoryId;
}
