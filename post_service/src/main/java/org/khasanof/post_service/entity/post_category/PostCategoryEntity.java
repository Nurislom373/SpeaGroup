package org.khasanof.post_service.entity.post_category;

import lombok.*;
import org.khasanof.post_service.entity.Auditable;
import org.khasanof.post_service.entity.category.CategoryEntity;
import org.khasanof.post_service.entity.post.PostEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document(collection = "post_category")
public class PostCategoryEntity extends Auditable {
    @DocumentReference
    @Field(name = "post_id")
    private PostEntity postId;
    @DocumentReference
    private List<CategoryEntity> categories;
}
