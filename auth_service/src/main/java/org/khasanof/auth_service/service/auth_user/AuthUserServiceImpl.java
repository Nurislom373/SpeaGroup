package org.khasanof.auth_service.service.auth_user;

import org.khasanof.auth_service.criteria.auth_user.AuthUserBetweenCriteria;
import org.khasanof.auth_service.criteria.auth_user.AuthUserCriteria;
import org.khasanof.auth_service.criteria.auth_user.AuthUserSearchCriteria;
import org.khasanof.auth_service.dto.auth_info.AuthInfoCreateDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserCreateDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserDetailDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserGetDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserUpdateDTO;
import org.khasanof.auth_service.entity.auth_info.AuthInfoEntity;
import org.khasanof.auth_service.entity.auth_role.AuthRoleEntity;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.enums.auth_role.AuthRoleEnum;
import org.khasanof.auth_service.enums.auth_user.AuthUserStatusEnum;
import org.khasanof.auth_service.mapper.auth_user.AuthUserMapper;
import org.khasanof.auth_service.predicate.auth_user.AuthUserPredicateExecutor;
import org.khasanof.auth_service.repository.auth_info.AuthInfoRepository;
import org.khasanof.auth_service.repository.auth_role.AuthRoleRepository;
import org.khasanof.auth_service.repository.auth_user.AuthUserRepository;
import org.khasanof.auth_service.service.AbstractService;
import org.khasanof.auth_service.service.auth_info.AuthInfoService;
import org.khasanof.auth_service.utils.BaseUtils;
import org.khasanof.auth_service.validator.auth_user.AuthUserValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class AuthUserServiceImpl extends AbstractService<
        AuthUserRepository,
        AuthUserMapper,
        AuthUserValidator> implements AuthUserService {

    private final MongoTemplate mongoTemplate;
    private final AuthRoleRepository roleRepository;
    private final AuthInfoRepository authInfoRepository;
    private final AuthInfoService authInfoService;

    public AuthUserServiceImpl(AuthUserRepository repository, AuthUserMapper mapper, AuthUserValidator validator, MongoTemplate mongoTemplate, AuthRoleRepository roleRepository, AuthInfoRepository authInfoRepository, AuthInfoService authInfoService) {
        super(repository, mapper, validator);
        this.mongoTemplate = mongoTemplate;
        this.roleRepository = roleRepository;
        this.authInfoRepository = authInfoRepository;
        this.authInfoService = authInfoService;
    }

    @Override
    public void create(AuthUserCreateDTO createDto) {
        validator.validCreateDTO(createDto);
        AuthUserEntity authUserEntity = mapper.toCreateDTO(createDto);
        authUserEntity.setStatus(AuthUserStatusEnum.NO_ACTIVE.getValue());
        authUserEntity.setPassword(BaseUtils.ENCODER.encode(createDto.getPassword()));
        AuthUserEntity entity = repository.insert(authUserEntity);
        roleRepository.insert(new AuthRoleEntity(entity, AuthRoleEnum.USER.getValue()));
        authInfoService.create(new AuthInfoCreateDTO(entity.getId(), BaseUtils.DEFAULT_CATEGORIES));
    }

    @Override
    public void update(AuthUserUpdateDTO updateDto) {
        validator.validUpdateDTO(updateDto);
        AuthUserEntity authUser = repository.findById(updateDto.getId()).orElseThrow(() -> new NotFoundException("User not found"));

        AuthUserEntity authUserUpdateEntity = mapper.toUpdateDTO(updateDto);

        if (updateDto.getFirstName().isBlank()) {
            authUserUpdateEntity.setFirstName(authUser.getFirstName());
        }
        if (updateDto.getLastName().isBlank()) {
            authUserUpdateEntity.setLastName(authUser.getLastName());
        }
        if (updateDto.getDescription().isBlank()) {
            authUserUpdateEntity.setDescription(authUser.getDescription());
        }
        if (updateDto.getEmail().isBlank()) {
            authUserUpdateEntity.setEmail(authUser.getEmail());
        }
        if (updateDto.getUsername().isBlank()) {
            authUserUpdateEntity.setUsername(authUser.getUsername());
        }
        if (updateDto.getLanguage().isBlank()) {
            authUserUpdateEntity.setLanguage(authUser.getLanguage());
        }
        if (updateDto.getImagePath().isBlank()) {
            authUserUpdateEntity.setImagePath(authUser.getImagePath());
        }
    }

    @Override
    public void delete(String id) {
        Optional<AuthUserEntity> authUser = repository.findById(id);
        if (authUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        repository.delete(authUser.get());
    }

    @Override
    public AuthUserGetDTO get(String id) {
        validator.validKey(id);
        AuthUserEntity authUser = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("User was not found by id %s".formatted(id));
        });
        return mapper.fromGetDTO(authUser);
    }

    @Override
    public AuthUserDetailDTO detail(String id) {
        validator.validKey(id);
        return mapper.fromDetailDTO(repository.findById(id).orElseThrow(() -> new NotFoundException("User not found")));
    }

    @Override
    public List<AuthUserGetDTO> list(AuthUserCriteria criteria) {
        return mapper.fromGetListDTO(
                repository.findAll(
                        PageRequest.of(
                                criteria.getPage(),
                                criteria.getSize(),
                                criteria.getSort(),
                                criteria.getFieldsEnum().getValue()
                        )
                ).stream().toList());
    }

    @Override
    public List<AuthUserGetDTO> getAllBlocked(AuthUserCriteria criteria) {
        return null;
    }

    @Override
    public void block(String id) {
        AuthUserEntity authUser = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("User was not found by id %s".formatted(id));
        });
    }

    @Override
    public void unblock(String id) {
        AuthUserEntity authUser = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("User was not found by id %s".formatted(id));
        });
    }

    @Override
    public boolean exist(String id) {
        return repository.existsById(id);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<AuthUserGetDTO> listWithSc(AuthUserSearchCriteria searchCriteria) {
        return mapper.fromGetListDTO(
                mongoTemplate.find(
                        new AuthUserPredicateExecutor.SearchPredicate(searchCriteria).searchQuery(),
                        AuthUserEntity.class));
    }

    @Override
    public List<AuthUserGetDTO> listWithBc(AuthUserBetweenCriteria betweenCriteria) {
        return mapper.fromGetListDTO(
                mongoTemplate.find(
                        new AuthUserPredicateExecutor.BetweenPredicate(betweenCriteria).betweenQuery(),
                        AuthUserEntity.class));
    }
}
