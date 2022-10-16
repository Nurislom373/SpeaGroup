package org.khasanof.auth_service.repository.auth_invite;

import org.khasanof.auth_service.entity.auth_invite.AuthInviteEntity;
import org.khasanof.auth_service.repository.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthInviteRepository extends MongoRepository<AuthInviteEntity, String>, BaseRepository {
}
