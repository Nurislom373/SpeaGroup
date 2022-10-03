package org.khasanof.post_service.entity.post_rating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.post_service.entity.Auditable;
import org.khasanof.post_service.entity.like.LikeEntity;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.entity.save.SaveEntity;
import org.khasanof.post_service.entity.view.ViewEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.LinkedList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "post_rating")
public class PostRatingEntity extends Auditable {
    @DocumentReference
    @Field(name = "post_id")
    private PostEntity postId;
    private LinkedList<LikeEntity> likes;
    private LinkedList<ViewEntity> views;
    private LinkedList<SaveEntity> saves;
    private Long likeCount;
    private Long viewsCount;
    private Long savesCount;
    private String ratingType;
    private String lastUpdateType;
}
