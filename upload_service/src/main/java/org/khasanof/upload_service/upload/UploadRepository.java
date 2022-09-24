package org.khasanof.upload_service.upload;

import org.khasanof.upload_service.upload.entity.UploadEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadRepository extends MongoRepository<UploadEntity, String> {
}
