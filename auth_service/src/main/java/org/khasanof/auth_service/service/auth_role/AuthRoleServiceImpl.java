package org.khasanof.auth_service.service.auth_role;

import org.khasanof.auth_service.criteria.auth_role.AuthRoleCriteria;
import org.khasanof.auth_service.dto.auth_role.AuthRoleCreateDTO;
import org.khasanof.auth_service.dto.auth_role.AuthRoleDetailDTO;
import org.khasanof.auth_service.dto.auth_role.AuthRoleGetDTO;
import org.khasanof.auth_service.entity.auth_role.AuthRoleEntity;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.enums.auth_role.AuthRoleEnum;
import org.khasanof.auth_service.mapper.auth_role.AuthRoleMapper;
import org.khasanof.auth_service.repository.auth_role.AuthRoleRepository;
import org.khasanof.auth_service.repository.auth_user.AuthUserRepository;
import org.khasanof.auth_service.service.AbstractService;
import org.khasanof.auth_service.validator.auth_role.AuthRoleValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class AuthRoleServiceImpl extends AbstractService<AuthRoleRepository, AuthRoleMapper, AuthRoleValidator> implements AuthRoleService {

    private final AuthUserRepository userRepository;

    public AuthRoleServiceImpl(AuthRoleRepository repository, AuthRoleMapper mapper, AuthRoleValidator validator, AuthUserRepository userRepository) {
        super(repository, mapper, validator);
        this.userRepository = userRepository;
    }

    @Override
    public void create(AuthRoleCreateDTO dto) {
        validator.validCreateDTO(dto);
        AuthUserEntity entity = userRepository.findById(dto.getAuthId()).orElseThrow(() -> {
            throw new NotFoundException("User not found");
        });
        checkRole(dto.getRole());
        AuthRoleEntity authRoleEntity = mapper.toCreateDTO(dto);
        authRoleEntity.setUserId(entity);
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
        AuthRoleEntity entity = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Role not found");
        });
        AuthRoleGetDTO authRoleGetDTO = mapper.fromGetDTO(entity);
        authRoleGetDTO.setAuthId(entity.getUserId().getId());
        return authRoleGetDTO;
    }

    @Override
    public AuthRoleDetailDTO detail(String id) {
        validator.validKey(id);
        AuthRoleEntity entity = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Role not found");
        });
        AuthRoleDetailDTO authRoleDetailDTO = mapper.fromDetailDTO(entity);
        authRoleDetailDTO.setUser(entity.getUserId());
        return authRoleDetailDTO;
    }

    @Override
    public List<AuthRoleGetDTO> list(AuthRoleCriteria criteria) {
        PageRequest request = PageRequest.of(criteria.getPage(), criteria.getSize(), criteria.getSort(), criteria.getFieldsEnum().getValue());
        return mapper.fromGetListDTO(repository.findAll(request).toList());
    }

    @Override
    public long count() {
        return repository.count();
    }

    private boolean checkRole(String role) {
        return AuthRoleEnum.hasRole(role);
    }
}
