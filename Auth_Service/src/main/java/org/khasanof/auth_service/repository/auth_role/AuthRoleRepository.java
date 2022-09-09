package org.khasanof.auth_service.repository.auth_role;

import org.khasanof.auth_service.entity.auth_role.AuthRoleEntity;
import org.khasanof.auth_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRoleRepository extends MongoRepository<AuthRoleEntity, String>, BaseRepository {
}
