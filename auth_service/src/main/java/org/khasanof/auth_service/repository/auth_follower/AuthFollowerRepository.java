package org.khasanof.auth_service.repository.auth_follower;

import org.khasanof.auth_service.entity.auth_follower.AuthFollowerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthFollowerRepository extends MongoRepository<AuthFollowerEntity, String> {
}
