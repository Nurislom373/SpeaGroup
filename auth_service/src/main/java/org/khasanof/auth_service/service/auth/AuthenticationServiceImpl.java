package org.khasanof.auth_service.service.auth;

import org.khasanof.auth_service.dto.auth.AuthRequestDTO;
import org.khasanof.auth_service.entity.auth_block.AuthBlockEntity;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.exception.exceptions.PasswordDoesNotMatchException;
import org.khasanof.auth_service.repository.auth_block.AuthBlockRepository;
import org.khasanof.auth_service.repository.auth_user.AuthUserRepository;
import org.khasanof.auth_service.utils.BaseUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthUserRepository userRepository;
    private final AuthBlockRepository blockRepository;
    private final MongoTemplate mongoTemplate;

    public AuthenticationServiceImpl(AuthUserRepository userRepository, AuthBlockRepository blockRepository, MongoTemplate mongoTemplate) {
        this.userRepository = userRepository;
        this.blockRepository = blockRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public String login(AuthRequestDTO dto) {
        Assert.notNull(dto, "DTO is must be not null!");
        AtomicInteger atomicInteger = new AtomicInteger(0);
        AuthUserEntity userEntity = mongoTemplate.findOne(Query.query(Criteria.where("email").is(dto.getEmailOrUsername())
                .orOperator(Criteria.where("username").is(dto.getEmailOrUsername()))), AuthUserEntity.class);
        if (Objects.isNull(userEntity))
            throw new RuntimeException("User not found");
        if (atomicInteger.get() == 3) {
//            TODO write user block logic!
        }
        if (!BaseUtils.ENCODER.matches(dto.getPassword(), userEntity.getPassword())) {
            atomicInteger.set(atomicInteger.get() + 1);
            throw new PasswordDoesNotMatchException("Invalid Password! try again");
        }
        return null;

    }

}
