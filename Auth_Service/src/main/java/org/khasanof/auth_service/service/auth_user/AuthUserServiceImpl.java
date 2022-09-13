package org.khasanof.auth_service.service.auth_user;

import org.khasanof.auth_service.criteria.GenericCriteria;

import org.khasanof.auth_service.criteria.auth_user.AuthUserBetweenCriteria;
import org.khasanof.auth_service.criteria.auth_user.AuthUserCriteria;
import org.khasanof.auth_service.criteria.auth_user.AuthUserSearchCriteria;
import org.khasanof.auth_service.dto.auth_user.AuthUserCreateDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserDetailDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserGetDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserUpdateDTO;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.mapper.auth_user.AuthUserMapper;
import org.khasanof.auth_service.repository.auth_user.AuthUserRepository;
import org.khasanof.auth_service.response.ApplicationError;
import org.khasanof.auth_service.response.Data;
import org.khasanof.auth_service.service.AbstractService;
import org.khasanof.auth_service.validator.auth_user.AuthUserValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthUserServiceImpl extends AbstractService<
        AuthUserRepository,
        AuthUserMapper,
        AuthUserValidator> implements AuthUserService {


    public AuthUserServiceImpl(AuthUserRepository repository, AuthUserMapper mapper, AuthUserValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public void create(AuthUserCreateDTO createDto) {
        validator.validCreateDTO(createDto);
        AuthUserEntity authUserEntity = mapper.toCreateDTO(createDto);
        repository.insert(authUserEntity);
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

    public ResponseEntity<Data<List<AuthUserGetDTO>>> getAllBlocked(GenericCriteria criteria) {
        return null;
    }

    public ResponseEntity<Data<Void>> block(String id) {
        AuthUserEntity authUser = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("User was not found by id %s".formatted(id));
        });

        return new ResponseEntity<>(new Data<>(true), HttpStatus.OK);
    }

    public ResponseEntity<Data<Void>> unblock(String id) {
        AuthUserEntity authUser = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("User was not found by id %s".formatted(id));
        });

        return new ResponseEntity<>(new Data<>(true), HttpStatus.OK);
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public List<AuthUserGetDTO> listWithSc(AuthUserSearchCriteria searchCriteria) {
        return null;
    }

    @Override
    public List<AuthUserBetweenCriteria> listWithBc(AuthUserBetweenCriteria BetweenCriteria) {
        return null;
    }
}
