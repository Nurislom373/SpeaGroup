package org.khasanof.post_service.repository.post_block;

import org.khasanof.post_service.entity.post_block.PostBlockEntity;
import org.khasanof.post_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostBlockRepository extends MongoRepository<PostBlockEntity, String>, BaseRepository {
}
