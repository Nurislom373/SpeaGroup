package org.khasanof.post_service.repository.category;

import org.khasanof.post_service.entity.category.CategoryEntity;
import org.khasanof.post_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CategoryRepository extends MongoRepository<CategoryEntity, String>, BaseRepository {

    boolean existsByCode(String code);

}
