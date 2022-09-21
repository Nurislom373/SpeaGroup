package org.khasanof.auth_service.service.auth;

import org.khasanof.auth_service.dto.auth.AuthRequestDTO;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.repository.auth_user.AuthUserRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Objects;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthUserRepository userRepository;
    private final MongoTemplate mongoTemplate;

    public AuthenticationServiceImpl(AuthUserRepository userRepository, MongoTemplate mongoTemplate) {
        this.userRepository = userRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public String login(AuthRequestDTO dto) {
        Assert.notNull(dto, "DTO is must be not null!");
        AuthUserEntity userEntity = mongoTemplate.findOne(Query.query(Criteria.where("email").is(dto.getEmailOrUsername())
                .orOperator(Criteria.where("username").is(dto.getEmailOrUsername()))), AuthUserEntity.class);
        if (Objects.isNull(userEntity)) throw new RuntimeException("User not found");
        return null;
    }
}
