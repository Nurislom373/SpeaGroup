package org.khasanof.post_service.repository.post_like;

import org.khasanof.post_service.entity.post_like.PostLikeEntity;
import org.khasanof.post_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends MongoRepository<PostLikeEntity, String>, BaseRepository {

    @Query("db.post_like.find({ 'post_id' : ?0 })")
    PostLikeEntity findByQuery(String id);

}
