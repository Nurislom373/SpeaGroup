package org.khasanof.question_service.entity.question_comment;

import lombok.*;
import org.khasanof.question_service.entity.Auditable;
import org.khasanof.question_service.entity.comment.CommentEntity;
import org.khasanof.question_service.entity.question.QuestionEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @author Nurislom
 * Date: 12/4/2022
 * Time: 7:27 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Document(collection = "question_comment")
public class QuestionCommentEntity extends Auditable {
    @DocumentReference
    @Field(name = "question_id")
    private QuestionEntity questionId;
    private List<CommentEntity> comments;
}
