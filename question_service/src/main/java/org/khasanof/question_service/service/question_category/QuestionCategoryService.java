package org.khasanof.question_service.service.question_category;

import org.khasanof.question_service.criteria.question_category.QuestionCategoryCriteria;
import org.khasanof.question_service.dto.question_category.QuestionCategoryCreateDTO;
import org.khasanof.question_service.dto.question_category.QuestionCategoryDetailDTO;
import org.khasanof.question_service.dto.question_category.QuestionCategoryGetDTO;
import org.khasanof.question_service.dto.question_category.QuestionCategoryUpdateDTO;
import org.khasanof.question_service.service.BaseService;
import org.khasanof.question_service.service.GenericCUDService;
import org.khasanof.question_service.service.GenericGDLService;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/7/2022
 * <br/>
 * Time: 10:12 PM
 * <br/>
 * Package: org.khasanof.question_service.service.question_category
 */
public interface QuestionCategoryService extends GenericCUDService<QuestionCategoryCreateDTO, QuestionCategoryUpdateDTO, String>,
        GenericGDLService<QuestionCategoryGetDTO, QuestionCategoryDetailDTO, String, QuestionCategoryCriteria>,
        BaseService {
}
