package org.khasanof.post_service.entity.post_share;

import lombok.*;
import org.khasanof.post_service.entity.Auditable;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.entity.share.ShareEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.LinkedList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "post_share")
@ToString
public class PostShareEntity extends Auditable {
    @DocumentReference
    @Field(name = "post_id")
    private PostEntity postId;
    private LinkedList<ShareEntity> shares;
}
