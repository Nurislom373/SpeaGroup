package org.khasanof.question_service.entity.question;

import lombok.*;
import org.khasanof.question_service.entity.Auditable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Nurislom
 * Date: 12/4/2022
 * Time: 7:23 PM
 */
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
    @Field(name = "is_solving")
    private boolean isSolving;
}
