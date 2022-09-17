package org.khasanof.auth_service.repository.auth_block;

import org.khasanof.auth_service.entity.auth_block.AuthBlockEntity;
import org.khasanof.auth_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthBlockRepository extends MongoRepository<AuthBlockEntity, String>, BaseRepository {
}
