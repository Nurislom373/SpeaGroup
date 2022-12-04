package org.khasanof.question_service.repository.question_category;

import org.khasanof.question_service.entity.question_category.QuestionCategoryEntity;
import org.khasanof.question_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nurislom
 * Date: 12/4/2022
 * Time: 7:48 PM
 */
@Repository
public interface QuestionCategoryRepository extends MongoRepository<QuestionCategoryEntity, String>, BaseRepository {
}
