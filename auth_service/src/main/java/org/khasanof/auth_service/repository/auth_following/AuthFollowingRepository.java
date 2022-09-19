package org.khasanof.auth_service.repository.auth_following;

import org.khasanof.auth_service.entity.auth_following.AuthFollowingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthFollowingRepository extends MongoRepository<AuthFollowingEntity, String> {
}
