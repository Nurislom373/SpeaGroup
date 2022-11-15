package org.khasanof.post_service.entity.post;

import lombok.*;
import org.khasanof.post_service.entity.Auditable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Document(collection = "post")
public class PostEntity extends Auditable {
    @Field(name = "user_id")
    private String userId;
    private String title;
    private String description;
    private String status;
    @Field(name = "media_paths")
    private List<String> mediaPaths;
    private String visibility;
}
