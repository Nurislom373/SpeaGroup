package org.khasanof.post_service.repository.post_category;

import org.khasanof.post_service.entity.post_category.PostCategoryEntity;
import org.khasanof.post_service.entity.post_comment.PostCommentEntity;
import org.khasanof.post_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostCategoryRepository extends MongoRepository<PostCategoryEntity, String>, BaseRepository {

    @Query("db.post_category.find({ 'post_id' : ?0 })")
    Optional<PostCategoryEntity> findByPostIdQuery(String id);
}
