package org.khasanof.question_service.entity.question_category;

import lombok.*;
import org.khasanof.question_service.entity.Auditable;
import org.khasanof.question_service.entity.question.QuestionEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.List;

/**
 * @author Nurislom
 * Date: 12/4/2022
 * Time: 7:11 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Document(collection = "question_category")
public class QuestionCategoryEntity extends Auditable {
    @DocumentReference
    @Field(name = "question_id")
    private QuestionEntity questionId;
    private List<String> categories;

    public QuestionCategoryEntity(String id, boolean isDeleted, Instant createdAt, String createdBy, Instant updatedAt, String updatedBy, QuestionEntity questionId, List<String> categories) {
        super(id, isDeleted, createdAt, createdBy, updatedAt, updatedBy);
        this.questionId = questionId;
        this.categories = categories;
    }
}
