package org.khasanof.question_service.repository.question;

import org.khasanof.question_service.entity.question.QuestionEntity;
import org.khasanof.question_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nurislom
 * Date: 12/4/2022
 * Time: 7:44 PM
 */
@Repository
public interface QuestionRepository extends MongoRepository<QuestionEntity, String>, BaseRepository {
}
