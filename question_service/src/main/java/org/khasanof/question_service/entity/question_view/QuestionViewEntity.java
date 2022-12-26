package org.khasanof.question_service.entity.question_view;

import lombok.*;
import org.khasanof.question_service.entity.Auditable;
import org.khasanof.question_service.entity.question.QuestionEntity;
import org.khasanof.question_service.entity.view.ViewEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/4/2022
 * <br/>
 * Time: 7:23 PM
 * <br/>
 * Package: org.khasanof.question_service.entity.question_view
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Document(collection = "question_view")
public class QuestionViewEntity extends Auditable {
    @DocumentReference
    @Field(name = "question_id")
    private QuestionEntity questionId;

    private List<ViewEntity> views;
}
