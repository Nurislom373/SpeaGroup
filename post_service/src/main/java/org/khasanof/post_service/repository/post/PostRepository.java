package org.khasanof.post_service.repository.post;

import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<PostEntity, String>, BaseRepository {

    List<PostEntity> findAllByCreatedBy(String id);

    List<PostEntity> findAllByCreatedByOrderByCreatedAtAsc(String id);

    void deleteAllByUserId(String id);

}
