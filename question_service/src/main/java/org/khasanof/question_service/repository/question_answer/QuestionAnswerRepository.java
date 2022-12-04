package org.khasanof.question_service.repository.question_answer;

import org.khasanof.question_service.entity.question_answer.QuestionAnswerEntity;
import org.khasanof.question_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nurislom
 * Date: 12/4/2022
 * Time: 7:46 PM
 */
@Repository
public interface QuestionAnswerRepository extends MongoRepository<QuestionAnswerEntity, String>, BaseRepository {
}
