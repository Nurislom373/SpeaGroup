package org.khasanof.question_service.repository.question_view;

import org.khasanof.question_service.entity.question_view.QuestionViewEntity;
import org.khasanof.question_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nurislom
 * Date: 12/6/2022
 * Time: 7:03 PM
 */
@Repository
public interface QuestionViewRepository extends MongoRepository<QuestionViewEntity, String>, BaseRepository {
}
