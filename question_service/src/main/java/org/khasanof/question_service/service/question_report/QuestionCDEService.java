package org.khasanof.question_service.service.question_report;

import org.khasanof.question_service.dto.question_report.QuestionReportCreateDTO;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/26/2022
 * <br/>
 * Time: 6:14 PM
 * <br/>
 * Package: org.khasanof.question_service.service.question_report
 */
public interface QuestionCDEService {

    void create(QuestionReportCreateDTO dto);

    void delete(String id);

    boolean existById(String id);

}
