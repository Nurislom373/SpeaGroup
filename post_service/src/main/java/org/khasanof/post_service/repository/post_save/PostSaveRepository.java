package org.khasanof.post_service.repository.post_save;

import org.khasanof.post_service.entity.post_save.PostSaveEntity;
import org.khasanof.post_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostSaveRepository extends MongoRepository<PostSaveEntity, String>, BaseRepository {

    @Query("db.post_save.find({ 'post_id' : ?0 })")
    Optional<PostSaveEntity> findByPostIdQuery(String id);

}
