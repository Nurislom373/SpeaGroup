package org.khasanof.question_service.repository.question_report;

import org.khasanof.question_service.entity.question_report.QuestionReportEntity;
import org.khasanof.question_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nurislom
 * Date: 12/6/2022
 * Time: 7:02 PM
 */
@Repository
public interface QuestionReportRepository extends MongoRepository<QuestionReportEntity, String>, BaseRepository {
}
