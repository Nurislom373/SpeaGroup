package org.khasanof.upload_service.upload;

import com.cloudinary.Cloudinary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouldinaryRepository extends MongoRepository<Cloudinary, String> {
}
