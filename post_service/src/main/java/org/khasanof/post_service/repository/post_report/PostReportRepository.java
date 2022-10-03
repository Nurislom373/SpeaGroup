package org.khasanof.post_service.repository.post_report;

import org.khasanof.post_service.entity.post_report.PostReportEntity;
import org.khasanof.post_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostReportRepository extends MongoRepository<PostReportEntity, String>, BaseRepository {
}
