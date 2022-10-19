package org.khasanof.upload_service.upload;

import org.khasanof.upload_service.upload.entity.CloudinaryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudinaryRepository extends MongoRepository<CloudinaryEntity, String> {

}
