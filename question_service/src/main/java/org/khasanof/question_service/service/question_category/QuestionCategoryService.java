package org.khasanof.question_service.service.question_category;

import org.khasanof.question_service.criteria.question_category.QuestionCategoryCriteria;
import org.khasanof.question_service.dto.question_category.*;
import org.khasanof.question_service.service.BaseService;
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
public interface QuestionCategoryService extends
        GenericGDLService<QuestionCategoryGetDTO, QuestionCategoryDetailDTO, String, QuestionCategoryCriteria>,
        BaseService {

    void create(QuestionCategoryCreateDTO dto);

    void addCategory(QuestionCategoryAddDTO dto);

    void deleteCategory(QuestionCategoryDeleteDTO dto);

    void delete(String id);

}
