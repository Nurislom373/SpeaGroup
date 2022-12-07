package org.khasanof.question_service.service.question;

import org.khasanof.question_service.criteria.question.QuestionCriteria;
import org.khasanof.question_service.dto.question.QuestionCreateDTO;
import org.khasanof.question_service.dto.question.QuestionDetailDTO;
import org.khasanof.question_service.dto.question.QuestionGetDTO;
import org.khasanof.question_service.dto.question.QuestionUpdateDTO;
import org.khasanof.question_service.entity.question.QuestionEntity;
import org.khasanof.question_service.service.BaseService;
import org.khasanof.question_service.service.GenericCUDService;
import org.khasanof.question_service.service.GenericGDLService;

/**
 * @author Nurislom
 * Date: 12/6/2022
 * Time: 7:36 PM
 */
public interface QuestionService extends GenericCUDService<QuestionCreateDTO, QuestionUpdateDTO, String>,
        GenericGDLService<QuestionGetDTO, QuestionDetailDTO, String, QuestionCriteria>,
        BaseService {

    boolean existById(String id);

    QuestionEntity findById(String id);

}
