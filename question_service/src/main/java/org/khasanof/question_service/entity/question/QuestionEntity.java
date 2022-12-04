package org.khasanof.question_service.entity.question;

import lombok.*;
import org.khasanof.question_service.entity.Auditable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Document(collection = "question")
public class QuestionEntity extends Auditable {
    @Field(name = "user_id")
    private String userId;
    private String title;
    @Field(name = "image_path")
    private String imagePath;
    @Field(name = "like_count")
    private Integer likeCount;
    @Field(name = "comment_count")
    private Integer commentCount;
    @Field(name = "is_solving")
    private boolean isSolving;
}