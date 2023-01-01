package org.khasanof.question_service.service.question_view;

import org.khasanof.question_service.criteria.question_view.QuestionViewCriteria;
import org.khasanof.question_service.dto.question_view.QuestionViewDetailDTO;
import org.khasanof.question_service.dto.question_view.QuestionViewGetDTO;
import org.khasanof.question_service.service.BaseService;
import org.khasanof.question_service.service.GenericGDLService;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/1/2023
 * <br/>
 * Time: 6:56 PM
 * <br/>
 * Package: org.khasanof.question_service.service.question_view
 */
public interface QuestionViewService extends GenericGDLService<QuestionViewGetDTO, QuestionViewDetailDTO, String, QuestionViewCriteria>,
        QuestionViewCDService, BaseService {
}
