package org.khasanof.auth_service.repository.blocked_for;

import org.khasanof.auth_service.entity.blocked_for.BlockedForEntity;
import org.khasanof.auth_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockedForRepository extends MongoRepository<BlockedForEntity, String>, BaseRepository {

    boolean existsByCode(String code);

}
