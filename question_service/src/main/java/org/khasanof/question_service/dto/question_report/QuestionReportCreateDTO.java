package org.khasanof.question_service.dto.question_report;

import lombok.*;
import org.khasanof.question_service.annotation.MongoIdConstraint;
import org.khasanof.question_service.dto.BaseDTO;
import org.khasanof.question_service.enums.report.ReportsEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/26/2022
 * <br/>
 * Time: 5:54 PM
 * <br/>
 * Package: org.khasanof.question_service.dto.question_report
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionReportCreateDTO implements BaseDTO {

    @NotBlank
    @MongoIdConstraint
    private String questionStrId;

    @NotNull
    private ReportsEnum reportCode;

    @NotBlank
    @MongoIdConstraint
    private String userId;

    @NotBlank
    @Size(min = 5, max = 2500, message = "message must be not null")
    private String message;

}
