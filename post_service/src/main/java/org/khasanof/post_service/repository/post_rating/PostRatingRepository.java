package org.khasanof.post_service.repository.post_rating;

import org.khasanof.post_service.entity.post_rating.PostRatingEntity;
import org.khasanof.post_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRatingRepository extends MongoRepository<PostRatingEntity, String>, BaseRepository {

    @Query("db.post_rating.find({ 'post_id' : ?0 })")
    Optional<PostRatingEntity> findByPostIdQuery(String id);
}
