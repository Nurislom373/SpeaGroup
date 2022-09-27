package org.khasanof.auth_service.service.auth;

import com.auth0.jwt.JWT;
import org.khasanof.auth_service.dto.auth.AuthRequestDTO;
import org.khasanof.auth_service.dto.auth_block.AuthBlockCreateDTO;
import org.khasanof.auth_service.dto.token.TokenDTO;
import org.khasanof.auth_service.entity.auth_role.AuthRoleEntity;
import org.khasanof.auth_service.entity.auth_token.AuthTokenEntity;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.exception.exceptions.PasswordDoesNotMatchException;
import org.khasanof.auth_service.repository.auth_role.AuthRoleRepository;
import org.khasanof.auth_service.repository.auth_token.AuthTokenRedisRepository;
import org.khasanof.auth_service.repository.auth_token.AuthTokenRepository;
import org.khasanof.auth_service.service.auth_block.AuthBlockService;
import org.khasanof.auth_service.utils.BaseUtils;
import org.khasanof.auth_service.utils.jwt.JWTUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.webjars.NotFoundException;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthBlockService authBlockService;
    private final MongoTemplate mongoTemplate;
    private final AuthTokenRedisRepository tokenRedisRepository;
    private final AuthTokenRepository authTokenRepository;
    private final AuthRoleRepository roleRepository;

    public AuthenticationServiceImpl(AuthBlockService authBlockService, MongoTemplate mongoTemplate, AuthTokenRedisRepository tokenRedisRepository, AuthTokenRepository authTokenRepository, AuthRoleRepository roleRepository) {
        this.authBlockService = authBlockService;
        this.mongoTemplate = mongoTemplate;
        this.tokenRedisRepository = tokenRedisRepository;
        this.authTokenRepository = authTokenRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public TokenDTO login(AuthRequestDTO dto) {
        Assert.notNull(dto, "DTO is must be not null!");
        AtomicInteger atomicInteger = new AtomicInteger(0);
        AuthUserEntity userEntity = mongoTemplate.findOne(Query.query(Criteria.where("email").is(dto.getEmailOrUsername())
                .orOperator(Criteria.where("username").is(dto.getEmailOrUsername()))), AuthUserEntity.class);

        if (Objects.isNull(userEntity))
            throw new RuntimeException("User not found");

        if (atomicInteger.get() == 3) {
            authBlockService.create(new AuthBlockCreateDTO(userEntity.getId(), 20, "251dyfdty56"));
        }

        if (!BaseUtils.ENCODER.matches(dto.getPassword(), userEntity.getPassword())) {
            atomicInteger.set(atomicInteger.get() + 1);
            throw new PasswordDoesNotMatchException("Invalid Password! try again");
        }

        Date expiryForAccessToken = JWTUtils.getExpiry();
        Date expiryForRefreshToken = JWTUtils.getExpiryForRefreshToken();

        AuthRoleEntity roleEntity = roleRepository.findById(userEntity.getId()).orElseThrow(() -> {
            throw new NotFoundException("Role not found");
        });

        String accessToken = JWT.create()
                .withSubject(userEntity.getId())
                .withSubject(userEntity.getUsername())
                .withClaim("role", roleEntity.getRole())
                .withClaim("email", userEntity.getEmail())
                .withExpiresAt(expiryForAccessToken)
                .sign(JWTUtils.getAlgorithm());

        String refreshToken = JWT.create()
                .withSubject(userEntity.getId())
                .withSubject(userEntity.getUsername())
                .withClaim("role", roleEntity.getRole())
                .withClaim("email", userEntity.getEmail())
                .withExpiresAt(expiryForRefreshToken)
                .sign(JWTUtils.getAlgorithm());

        AuthTokenEntity tokenEntity = authTokenRepository.save(
                new AuthTokenEntity(userEntity, accessToken, BaseUtils.currentTimeAddMinute(50)
                ));
        tokenRedisRepository.save(tokenEntity);

        return TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}
