package org.khasanof.auth_service.service.auth;

import com.auth0.jwt.JWT;
import org.khasanof.auth_service.dto.auth.AuthChangeImagePathDTO;
import org.khasanof.auth_service.dto.auth.AuthChangePasswordDTO;
import org.khasanof.auth_service.dto.auth.AuthRequestDTO;
import org.khasanof.auth_service.dto.auth_block.AuthBlockCreateDTO;
import org.khasanof.auth_service.dto.token.TokenDTO;
import org.khasanof.auth_service.entity.auth_role.AuthRoleEntity;
import org.khasanof.auth_service.entity.auth_token.AuthTokenEntity;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.enums.auth_token.AuthTokenType;
import org.khasanof.auth_service.enums.auth_user.AuthUserStatusEnum;
import org.khasanof.auth_service.exception.exceptions.InvalidValidationException;
import org.khasanof.auth_service.exception.exceptions.PasswordDoesNotMatchException;
import org.khasanof.auth_service.repository.auth_role.AuthRoleRepository;
import org.khasanof.auth_service.repository.auth_token.AuthTokenRedisRepository;
import org.khasanof.auth_service.repository.auth_token.AuthTokenRepository;
import org.khasanof.auth_service.repository.auth_user.AuthUserRepository;
import org.khasanof.auth_service.service.auth_block.AuthBlockService;
import org.khasanof.auth_service.service.auth_user.AuthUserService;
import org.khasanof.auth_service.utils.BaseUtils;
import org.khasanof.auth_service.utils.jwt.JWTUtils;
import org.khasanof.auth_service.validator.auth_user.AuthUserValidator;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.webjars.NotFoundException;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthBlockService authBlockService;
    private final AuthUserService authUserService;
    private final MongoTemplate mongoTemplate;
    private final AuthTokenRedisRepository tokenRedisRepository;
    private final AuthTokenRepository authTokenRepository;
    private final AuthRoleRepository roleRepository;
    private final AuthUserRepository authUserRepository;
    private final AuthUserValidator userValidator;

    public AuthenticationServiceImpl(AuthBlockService authBlockService, @Lazy AuthUserService authUserService, MongoTemplate mongoTemplate, AuthTokenRedisRepository tokenRedisRepository, AuthTokenRepository authTokenRepository, AuthRoleRepository roleRepository, AuthUserRepository authUserRepository, AuthUserValidator userValidator) {
        this.authBlockService = authBlockService;
        this.authUserService = authUserService;
        this.mongoTemplate = mongoTemplate;
        this.tokenRedisRepository = tokenRedisRepository;
        this.authTokenRepository = authTokenRepository;
        this.roleRepository = roleRepository;
        this.authUserRepository = authUserRepository;
        this.userValidator = userValidator;
    }

    @Override
    public TokenDTO login(AuthRequestDTO dto) {
        Assert.notNull(dto, "DTO is must be not null!");
        AtomicInteger atomicInteger = new AtomicInteger(0);
        AuthUserEntity userEntity = mongoTemplate.findOne(
                Query.query(Criteria.where("email")
                        .is(dto.getEmailOrUsername())
                        .orOperator(Criteria.where("username")
                                .is(dto.getEmailOrUsername()))),
                AuthUserEntity.class);

        if (Objects.isNull(userEntity))
            throw new RuntimeException("User not found");

        if (atomicInteger.get() == 3) {
            authBlockService.create(new AuthBlockCreateDTO(userEntity.getId(),
                    "633568fb6f2f9218191858d3"));
        }

        if (!BaseUtils.ENCODER.matches(dto.getPassword(), userEntity.getPassword())) {
            atomicInteger.set(atomicInteger.get() + 1);
            throw new PasswordDoesNotMatchException("Invalid Password! try again");
        }

        Date expiryForAccessToken = JWTUtils.getExpiry();
        Date expiryForRefreshToken = JWTUtils.getExpiryForRefreshToken();

        AuthRoleEntity roleEntity = roleRepository.findById(userEntity.getId())
                .orElseThrow(() -> {
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

        AuthTokenEntity access = authTokenRepository.save(
                new AuthTokenEntity(userEntity, AuthTokenType.ACCESS,
                        accessToken, BaseUtils.currentTimeAddMinute(50)));

        AuthTokenEntity refresh = authTokenRepository.save(
                new AuthTokenEntity(userEntity, AuthTokenType.REFRESH,
                        accessToken, BaseUtils.currentTimeAddMinute((3 * 24 * 60))));

        tokenRedisRepository.save(access);
        tokenRedisRepository.save(refresh);

        return TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void verifiedEmail(String userId) {
        userValidator.validKey(userId);
        AuthUserEntity entity = authUserRepository.findById(userId)
                .orElseThrow(() -> {
                    throw new NotFoundException("User not found");
                });
        entity.setStatus(AuthUserStatusEnum.ACTIVE);
        entity.setVerified(true);
        authUserRepository.save(entity);
    }

    @Override
    public void changePassword(AuthChangePasswordDTO dto) {
        Assert.notNull(dto, "DTO must be not null!");
        AuthUserEntity userEntity = authUserService.getEntity(dto.getUserId());
        if (!BaseUtils.ENCODER.matches(dto.getOldPwd(), userEntity.getPassword())) {
            throw new PasswordDoesNotMatchException("Invalid Password! try again");
        }
        if (dto.getNewPwd().length() < 8 || dto.getNewPwd().length() > 120) {
            throw new InvalidValidationException("Password min 8 max 120 length must be required!");
        }
        userEntity.setPassword(BaseUtils.ENCODER.encode(dto.getNewPwd()));
        userEntity.setUpdatedAt(Instant.now());
        userEntity.setUpdatedBy(dto.getUserId());
        authUserRepository.save(userEntity);
    }

    @Override
    public void changeImagePath(AuthChangeImagePathDTO dto) {
        Assert.notNull(dto, "DTO must be not null!");
        AuthUserEntity userEntity = authUserService.getEntity(dto.getUserId());
        userEntity.setImagePath(dto.getNewImagePath());
        authUserRepository.save(userEntity);
    }

}
