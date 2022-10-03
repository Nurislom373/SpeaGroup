package org.khasanof.post_service.repository.post_save;

import org.khasanof.post_service.entity.post_save.PostSaveEntity;
import org.khasanof.post_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostSaveRepository extends MongoRepository<PostSaveEntity, String>, BaseRepository {
}
