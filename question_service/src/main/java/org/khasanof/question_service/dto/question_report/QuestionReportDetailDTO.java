package org.khasanof.question_service.dto.question_report;

import lombok.*;
import org.khasanof.question_service.dto.GenericDTO;
import org.khasanof.question_service.entity.question.QuestionEntity;
import org.khasanof.question_service.entity.report.ReportEntity;

import java.time.Instant;
import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/26/2022
 * <br/>
 * Time: 6:03 PM
 * <br/>
 * Package: org.khasanof.question_service.dto.question_report
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class QuestionReportDetailDTO extends GenericDTO {
    private QuestionEntity question;
    private List<ReportEntity> reports;
    private Integer count;
    private Integer totalPointReports;
    private Instant updatedAt;
    private String updatedBy;
    private Instant createdAt;
}
