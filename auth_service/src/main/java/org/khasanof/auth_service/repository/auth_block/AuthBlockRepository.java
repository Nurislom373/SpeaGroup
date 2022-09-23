package org.khasanof.auth_service.repository.auth_block;

import org.khasanof.auth_service.entity.auth_block.AuthBlockEntity;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthBlockRepository extends MongoRepository<AuthBlockEntity, String>, BaseRepository {

    AuthBlockEntity findByUserId(String userId);

}
