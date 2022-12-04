package org.khasanof.question_service.entity.question_report;

import lombok.*;
import org.khasanof.question_service.entity.Auditable;
import org.khasanof.question_service.entity.question.QuestionEntity;
import org.khasanof.question_service.entity.report.ReportEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @author Nurislom
 * Date: 12/4/2022
 * Time: 7:24 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Document(collection = "question_report")
public class QuestionReportEntity extends Auditable {
    @DocumentReference
    @Field(name = "question_id")
    private QuestionEntity questionId;
    private List<ReportEntity> reports;
}
