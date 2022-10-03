package org.khasanof.post_service.repository.post_category;

import org.khasanof.post_service.entity.post_category.PostCategoryEntity;
import org.khasanof.post_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PostCategoryRepository extends MongoRepository<PostCategoryEntity, String>, BaseRepository {
}
