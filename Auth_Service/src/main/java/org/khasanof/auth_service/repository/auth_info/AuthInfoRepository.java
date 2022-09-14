package org.khasanof.auth_service.repository.auth_info;

import org.khasanof.auth_service.entity.auth_info.AuthInfoEntity;
import org.khasanof.auth_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthInfoRepository extends MongoRepository<AuthInfoEntity, String>, BaseRepository {
    Optional<AuthInfoEntity> findByUserIdEquals(String userId);

}
