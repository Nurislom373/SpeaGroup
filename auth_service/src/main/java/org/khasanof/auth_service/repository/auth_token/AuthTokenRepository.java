package org.khasanof.auth_service.repository.auth_token;

import org.khasanof.auth_service.entity.auth_token.AuthTokenEntity;
import org.khasanof.auth_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthTokenRepository extends MongoRepository<AuthTokenEntity, String>, BaseRepository {
}
