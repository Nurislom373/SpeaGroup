package org.khasanof.post_service.repository.post_like;

import org.khasanof.post_service.entity.post_like.PostLikeEntity;
import org.khasanof.post_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PostLikeRepository extends MongoRepository<PostLikeEntity, String>, BaseRepository {
}
