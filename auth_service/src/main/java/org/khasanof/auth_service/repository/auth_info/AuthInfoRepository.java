package org.khasanof.auth_service.repository.auth_info;

import org.khasanof.auth_service.entity.auth_info.AuthInfoEntity;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthInfoRepository extends MongoRepository<AuthInfoEntity, String>, BaseRepository {

    Optional<AuthInfoEntity> findByUserIdEquals(AuthUserEntity userId);

    boolean existsByUserIdEquals(AuthUserEntity userId);

    void deleteByUserId(AuthUserEntity userId);

    @Query("db.auth_info.find({_id: ?1}, {_id:0})")
    List<String> findAllById(String id);

}
