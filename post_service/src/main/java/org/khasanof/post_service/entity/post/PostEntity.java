package org.khasanof.post_service.entity.post;

import lombok.*;
import org.khasanof.post_service.entity.Auditable;
import org.khasanof.post_service.entity.auth_user.AuthUserEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "post")
public class PostEntity extends Auditable {
    @DocumentReference
    @Field(name = "user_id")
    private AuthUserEntity userId;
    private String title;
    private String description;
    private String status;
    @Field(name = "media_paths")
    private List<String> mediaPaths;
    @Field(name = "like_count")
    private Integer likeCount;
    @Field(name = "comment_count")
    private Integer commentCount;
    private String visibility;
}
