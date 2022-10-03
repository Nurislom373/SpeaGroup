package org.khasanof.post_service.repository.post_view;

import org.khasanof.post_service.entity.post_view.PostViewEntity;
import org.khasanof.post_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PostViewRepository extends MongoRepository<PostViewEntity, String>, BaseRepository {
}
