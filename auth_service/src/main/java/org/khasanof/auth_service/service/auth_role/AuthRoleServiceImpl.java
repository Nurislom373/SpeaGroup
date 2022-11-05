package org.khasanof.auth_service.service.auth_role;

import org.khasanof.auth_service.criteria.auth_role.AuthRoleCriteria;
import org.khasanof.auth_service.dto.auth_role.AuthRoleCreateDTO;
import org.khasanof.auth_service.dto.auth_role.AuthRoleDetailDTO;
import org.khasanof.auth_service.dto.auth_role.AuthRoleGetDTO;
import org.khasanof.auth_service.entity.auth_role.AuthRoleEntity;
import org.khasanof.auth_service.exception.exceptions.AlreadyCreatedException;
import org.khasanof.auth_service.mapper.auth_role.AuthRoleMapper;
import org.khasanof.auth_service.repository.auth_role.AuthRoleRepository;
import org.khasanof.auth_service.service.AbstractService;
import org.khasanof.auth_service.service.auth_user.AuthUserService;
import org.khasanof.auth_service.validator.auth_role.AuthRoleValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Objects;

@Service
public class AuthRoleServiceImpl extends AbstractService<AuthRoleRepository, AuthRoleMapper, AuthRoleValidator> implements AuthRoleService {

    private final AuthUserService userService;
    private final MongoTemplate mongoTemplate;

    public AuthRoleServiceImpl(AuthRoleRepository repository, AuthRoleMapper mapper, AuthRoleValidator validator, AuthUserService userService, MongoTemplate mongoTemplate) {
        super(repository, mapper, validator);
        this.userService = userService;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void create(AuthRoleCreateDTO dto) {
        validator.validCreateDTO(dto);
        AuthRoleEntity entity = mongoTemplate.findOne(
                Query.query(new Criteria("userId")
                        .is(userService.getEntity(dto.getAuthId()))),
                AuthRoleEntity.class);
        System.out.println("entity = " + entity);
        if (Objects.nonNull(entity)) {
            throw new AlreadyCreatedException("User Role Already Created!");
        }
        AuthRoleEntity authRoleEntity = mapper.toCreateDTO(dto);
        authRoleEntity.setUserId(userService.getEntity(dto.getAuthId()));
        repository.save(authRoleEntity);
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (repository.existsById(id))
            repository.deleteById(id);
        else
            throw new NotFoundException("Role not found");
    }

    @Override
    public AuthRoleGetDTO get(String id) {
        validator.validKey(id);
        return returnToGetDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Role not found");
                        }));
    }

    @Override
    public AuthRoleDetailDTO detail(String id) {
        validator.validKey(id);
        return mapper.fromDetailDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Role not found");
                        }));
    }

    @Override
    public List<AuthRoleGetDTO> list(AuthRoleCriteria criteria) {
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

    private AuthRoleGetDTO returnToGetDTO(AuthRoleEntity entity) {
        AuthRoleGetDTO authRoleGetDTO = mapper.fromGetDTO(entity);
        authRoleGetDTO.setAuthId(entity.getUserId().getId());
        return authRoleGetDTO;
    }
}
