package org.khasanof.auth_service.service.auth_token;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.auth_service.criteria.auth_token.AuthTokenCriteria;
import org.khasanof.auth_service.criteria.auth_token.AuthTokenTypeCriteria;
import org.khasanof.auth_service.dto.auth_token.AuthTokenCreateDTO;
import org.khasanof.auth_service.dto.auth_token.AuthTokenDetailDTO;
import org.khasanof.auth_service.dto.auth_token.AuthTokenGetDTO;
import org.khasanof.auth_service.entity.auth_token.AuthTokenEntity;
import org.khasanof.auth_service.mapper.auth_token.AuthTokenMapper;
import org.khasanof.auth_service.repository.auth_token.AuthTokenRedisRepository;
import org.khasanof.auth_service.repository.auth_token.AuthTokenRepository;
import org.khasanof.auth_service.service.AbstractService;
import org.khasanof.auth_service.service.auth_user.AuthUserService;
import org.khasanof.auth_service.validator.auth_token.AuthTokenValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@EnableScheduling
@Slf4j
public class AuthTokenServiceImpl extends AbstractService<AuthTokenRepository, AuthTokenMapper, AuthTokenValidator> implements AuthTokenService {

    private final AuthUserService userService;
    private final MongoTemplate mongoTemplate;
    private final AuthTokenRedisRepository redisRepository;

    public AuthTokenServiceImpl(AuthTokenRepository repository, AuthTokenMapper mapper, AuthTokenValidator validator, AuthUserService userService, MongoTemplate mongoTemplate, AuthTokenRedisRepository redisRepository) {
        super(repository, mapper, validator);
        this.userService = userService;
        this.mongoTemplate = mongoTemplate;
        this.redisRepository = redisRepository;
    }

    @Override
    public void create(AuthTokenCreateDTO dto) {
        validator.validCreateDTO(dto);
        AuthTokenEntity tokenEntity = mongoTemplate.findOne(
                Query.query(Criteria.where("userId")
                        .is(userService.getEntity(dto.getAuthId()))
                        .orOperator(Criteria.where("type")
                                .is(dto.getType()))), AuthTokenEntity.class);
        if (Objects.nonNull(tokenEntity)) {
            tokenEntity.setToken(dto.getToken());
            tokenEntity.setDuration(changeIntegerMinToTime(dto.getMinTime()));
            tokenEntity.setUpdatedAt(Instant.now());
            tokenEntity.setUpdatedBy(dto.getAuthId());
            repository.save(tokenEntity);
        } else {
            AuthTokenEntity authTokenEntity = mapper.toCreateDTO(dto);
            authTokenEntity.setUserId(userService.getEntity(dto.getAuthId()));
            authTokenEntity.setDuration(changeIntegerMinToTime(dto.getMinTime()));
            repository.save(authTokenEntity);
        }
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (repository.existsById(id))
            repository.deleteById(id);
        else
            throw new NotFoundException("Token not found");
    }

    @Override
    public List<AuthTokenGetDTO> listType(AuthTokenTypeCriteria criteria) {
        return mongoTemplate.find(
                        Query.query(Criteria.where("type")
                                .is(criteria.getType())).with(PageRequest.of(criteria.getPage(),
                                criteria.getSize())), AuthTokenEntity.class)
                .stream().map(this::returnToGetDTO).toList();
    }

    @Override
    public AuthTokenGetDTO get(String id) {
        validator.validKey(id);
        return returnToGetDTO(
                repository.findById(id)
                        .orElseThrow(() -> new NotFoundException("token not found")));
    }

    @Override
    public AuthTokenDetailDTO detail(String id) {
        validator.validKey(id);
        return mapper.fromDetailDTO(
                repository.findById(id)
                        .orElseThrow(() -> new NotFoundException("token not found")));
    }

    @Override
    public List<AuthTokenGetDTO> list(AuthTokenCriteria criteria) {
        return repository.findAll(
                        PageRequest.of(criteria.getPage(), criteria.getSize(),
                                criteria.getSort(), criteria.getFieldsEnum().getValue())
                ).stream()
                .map(this::returnToGetDTO)
                .toList();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
//    @Scheduled(fixedDelay = 30000)
    public void autoIsDead() {
        repository.findAll().stream()
                .filter(token -> token.getDuration()
                        .isAfter(Instant.now()))
                .peek(obj -> obj.setDead(true))
                .forEach(repository::save);
        log.info("isDead set token when expiry is end");
    }

    @Override
    public void addRedis(AuthTokenEntity entity) {
        redisRepository.save(entity);
    }

    @Override
    public void deleteRedis(String id) {

    }

    @Override
    public AuthTokenGetDTO getRedis(String id) {
        return null;
    }

    @Override
    public AuthTokenDetailDTO detailRedis(String id) {
        return null;
    }

    @Override
    public List<AuthTokenGetDTO> listRedis() {
        return null;
    }

    private AuthTokenGetDTO returnToGetDTO(AuthTokenEntity entity) {
        AuthTokenGetDTO authTokenGetDTO = mapper.fromGetDTO(entity);
        authTokenGetDTO.setAuthUserId(entity.getUserId().getId());
        return authTokenGetDTO;
    }

    private Instant changeIntegerMinToTime(Integer minTime) {
        return Instant.now().plusNanos(TimeUnit.MINUTES.toNanos(minTime));
    }
}
