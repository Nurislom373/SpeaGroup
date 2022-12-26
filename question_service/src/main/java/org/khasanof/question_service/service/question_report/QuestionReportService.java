package org.khasanof.question_service.service.question_report;

import org.khasanof.question_service.criteria.question_report.QuestionReportCriteria;
import org.khasanof.question_service.dto.question_report.QuestionReportDetailDTO;
import org.khasanof.question_service.dto.question_report.QuestionReportGetDTO;
import org.khasanof.question_service.service.BaseService;
import org.khasanof.question_service.service.GenericGDLService;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/26/2022
 * <br/>
 * Time: 6:10 PM
 * <br/>
 * Package: org.khasanof.question_service.service.question_report
 */
public interface QuestionReportService extends GenericGDLService<QuestionReportGetDTO,
        QuestionReportDetailDTO, String, QuestionReportCriteria>, QuestionCDEService, BaseService {

}
