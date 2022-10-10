package org.khasanof.post_service.repository.post_share;

import org.khasanof.post_service.entity.post_share.PostShareEntity;
import org.khasanof.post_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostShareRepository extends MongoRepository<PostShareEntity, String>, BaseRepository {
}
