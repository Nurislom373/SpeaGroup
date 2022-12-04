package org.khasanof.question_service.entity.question_answer;

import lombok.*;
import org.khasanof.question_service.entity.Auditable;
import org.khasanof.question_service.entity.answer.AnswerEntity;
import org.khasanof.question_service.entity.question.QuestionEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @author Nurislom
 * Date: 12/4/2022
 * Time: 5:47 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Document(collection = "question_answer")
public class QuestionAnswerEntity extends Auditable {
    @DocumentReference
    @Field(name = "question_id")
    private QuestionEntity questionId;
    @DocumentReference
    private List<AnswerEntity> answers;
}
