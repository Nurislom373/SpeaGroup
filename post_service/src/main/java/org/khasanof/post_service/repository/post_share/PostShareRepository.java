package org.khasanof.post_service.repository.post_share;

import org.khasanof.post_service.entity.post_share.PostShareEntity;
import org.khasanof.post_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostShareRepository extends MongoRepository<PostShareEntity, String>, BaseRepository {

    @Query("db.post_share.find({ 'post_id' : ?0 })")
    Optional<PostShareEntity> findByPostIdQuery(String id);

}
