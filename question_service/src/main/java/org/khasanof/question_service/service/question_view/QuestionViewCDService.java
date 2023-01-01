package org.khasanof.question_service.service.question_view;

import org.khasanof.question_service.dto.question_view.QuestionViewCreateDTO;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/1/2023
 * <br/>
 * Time: 6:59 PM
 * <br/>
 * Package: org.khasanof.question_service.service.question_view
 */
public interface QuestionViewCDService {

    void create(QuestionViewCreateDTO dto);

    void delete(String id);

}
