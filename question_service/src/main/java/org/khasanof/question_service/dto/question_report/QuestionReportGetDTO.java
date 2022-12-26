package org.khasanof.question_service.dto.question_report;

import lombok.*;
import org.khasanof.question_service.dto.GenericDTO;

import java.time.Instant;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/26/2022
 * <br/>
 * Time: 6:01 PM
 * <br/>
 * Package: org.khasanof.question_service.dto.question_report
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class QuestionReportGetDTO extends GenericDTO {
    private String questionStrId;
    private Integer reportsCount;
    private Integer totalPoint;
    private Instant lastReportTime;
}
