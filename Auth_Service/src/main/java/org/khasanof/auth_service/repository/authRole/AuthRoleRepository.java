package org.khasanof.auth_service.repository.authRole;

import org.khasanof.auth_service.entity.authRole.AuthRoleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRoleRepository extends MongoRepository<AuthRoleEntity, String> {
}
