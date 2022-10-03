package org.khasanof.post_service.repository.auth_user;

import org.khasanof.post_service.entity.auth_user.AuthUserEntity;
import org.khasanof.post_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepository extends MongoRepository<AuthUserEntity, String>, BaseRepository {
}
