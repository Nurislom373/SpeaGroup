package org.khasanof.auth_service.service.auth_block;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.auth_service.criteria.auth_block.AuthBlockCriteria;
import org.khasanof.auth_service.dto.auth_block.AuthBlockCreateDTO;
import org.khasanof.auth_service.dto.auth_block.AuthBlockDetailDTO;
import org.khasanof.auth_service.dto.auth_block.AuthBlockGetDTO;
import org.khasanof.auth_service.entity.auth_block.AuthBlockEntity;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.entity.blocked_for.BlockedForEntity;
import org.khasanof.auth_service.mapper.auth_block.AuthBlockMapper;
import org.khasanof.auth_service.repository.auth_block.AuthBlockRepository;
import org.khasanof.auth_service.repository.auth_user.AuthUserRepository;
import org.khasanof.auth_service.repository.blocked_for.BlockedForRepository;
import org.khasanof.auth_service.service.AbstractService;
import org.khasanof.auth_service.service.auth_user.AuthUserService;
import org.khasanof.auth_service.utils.BaseUtils;
import org.khasanof.auth_service.validator.auth_block.AuthBlockValidator;
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
public class AuthBlockServiceImpl extends AbstractService<AuthBlockRepository, AuthBlockMapper, AuthBlockValidator> implements AuthBlockService {

    private final AuthUserRepository userRepository;
    private final BlockedForRepository blockedForRepository;
    private final AuthUserService userService;
    private final MongoTemplate mongoTemplate;

    public AuthBlockServiceImpl(AuthBlockRepository repository, AuthBlockMapper mapper, AuthBlockValidator validator, AuthUserRepository userRepository, BlockedForRepository blockedForRepository, AuthUserService userService, MongoTemplate mongoTemplate) {
        super(repository, mapper, validator);
        this.userRepository = userRepository;
        this.blockedForRepository = blockedForRepository;
        this.userService = userService;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void create(AuthBlockCreateDTO dto) {
        validator.validCreateDTO(dto);
        AuthBlockEntity authBlock = mongoTemplate.findOne(
                Query.query(new Criteria("userId")
                        .is(userService.getEntity(dto.getAuthId()))),
                AuthBlockEntity.class);
        if (Objects.nonNull(authBlock)) {
            throw new RuntimeException("This user already blocked!");
        }
        AuthUserEntity userEntity = userRepository.findById(dto.getAuthId())
                .orElseThrow(() -> {
                    throw new NotFoundException("User not found");
                });
        BlockedForEntity blockedForEntity = blockedForRepository.findById(dto.getBlockedForId())
                .orElseThrow(() -> {
                    throw new NotFoundException("Blocked For not found");
                });
        repository.save(
                AuthBlockEntity.builder()
                        .blockedFor(blockedForEntity)
                        .userId(userEntity)
                        .duration(BaseUtils.currentTimeAddMinute(blockedForEntity.getTime()))
                        .build()
        );
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id))
            throw new NotFoundException("Auth Block not found");
        repository.deleteById(id);
    }

    @Override
    @Scheduled(fixedDelay = 30000)
    public void autoDeleteTimeOut() {
        BaseUtils.EXECUTOR_SERVICE.execute(() -> repository.findAll()
                .parallelStream()
                .filter(block -> block.getDuration()
                        .isAfter(Instant.now()))
                .forEach(repository::delete));
        log.info("Auto Delete Start to find");
    }

    @Override
    public AuthBlockGetDTO get(String id) {
        validator.validKey(id);
        return returnToGetDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Auth Block not found");
                        })
        );
    }

    @Override
    public AuthBlockDetailDTO detail(String id) {
        return mapper.fromDetailDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Auth Block not found");
                        })
        );
    }

    @Override
    public List<AuthBlockGetDTO> list(AuthBlockCriteria criteria) {
        return repository.findAll(
                        PageRequest.of(criteria.getPage(),
                                criteria.getSize()))
                .stream()
                .map(this::returnToGetDTO)
                .toList();
    }

    private AuthBlockGetDTO returnToGetDTO(AuthBlockEntity entity) {
        AuthBlockGetDTO authBlockGetDTO = mapper.fromGetDTO(entity);
        authBlockGetDTO.setAuthId(entity.getUserId().getId());
        authBlockGetDTO.setBlockedForCode(entity.getBlockedFor().getCode());
        return authBlockGetDTO;
    }

    private Instant minusToNow(Instant time) {
        return Instant.now()
                .minusNanos(TimeUnit.MINUTES.toNanos(time.getNano()));
    }
}
