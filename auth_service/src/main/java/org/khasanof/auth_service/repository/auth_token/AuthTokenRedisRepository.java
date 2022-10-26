package org.khasanof.auth_service.repository.auth_token;

import org.khasanof.auth_service.entity.auth_token.AuthTokenEntity;
import org.khasanof.auth_service.repository.BaseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Objects;

@Repository
public class AuthTokenRedisRepository implements BaseRepository {

    private final HashOperations hashOperations;
    public static final String HASH_KEY = "Token";

    public AuthTokenRedisRepository(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public AuthTokenEntity save(AuthTokenEntity entity) {
        hashOperations.put(HASH_KEY, entity.getId(), entity);
        return entity;
    }

    public AuthTokenEntity update(AuthTokenEntity token) {
        AuthTokenEntity authTokenEntity = (AuthTokenEntity) hashOperations.get(HASH_KEY, token.getId());
        if (Objects.isNull(authTokenEntity))
            throw new NotFoundException("Token not found");
        BeanUtils.copyProperties(token, authTokenEntity);
        return save(authTokenEntity);
    }

    public List<AuthTokenEntity> findAll() {
        return hashOperations.values(HASH_KEY);
    }

    public void delete(String id) {
        hashOperations.delete(HASH_KEY, id);
    }

    public AuthTokenEntity get(String id) {
        return (AuthTokenEntity) hashOperations.get(HASH_KEY, id);
    }

    public Long count() {
        return hashOperations.size(HASH_KEY);
    }

}
