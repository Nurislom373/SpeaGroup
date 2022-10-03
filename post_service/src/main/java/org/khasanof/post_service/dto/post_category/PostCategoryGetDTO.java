package org.khasanof.post_service.dto.post_category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.post_service.dto.GenericDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostCategoryGetDTO extends GenericDTO {
    private String postId;
    private List<String> categoryId;
}
