package org.khasanof.post_service.dto.post_category;

import lombok.*;
import org.khasanof.post_service.dto.BaseDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostCategoryAddAllDTO implements BaseDTO {
    private String categoryPostId;
    private List<String> categoryIds;
}
