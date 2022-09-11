package org.khasanof.auth_service.service.auth;

import org.khasanof.auth_service.criteria.GenericCriteria;

import org.khasanof.auth_service.dto.auth.AuthUserCreateDTO;
import org.khasanof.auth_service.dto.auth.AuthUserDTO;
import org.khasanof.auth_service.dto.auth.AuthUserUpdateDTO;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.mapper.auth.AuthUserMapper;
import org.khasanof.auth_service.repository.auth_user.AuthUserRepository;
import org.khasanof.auth_service.response.ApplicationError;
import org.khasanof.auth_service.response.Data;
import org.khasanof.auth_service.service.AbstractService;
import org.khasanof.auth_service.validator.auth.AuthUserValidator;
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
    public ResponseEntity<Data<String>> create(AuthUserCreateDTO createDto) {
        validator.validCreateDTO(createDto);
        AuthUserEntity authUserEntity = mapper.fromCreateDto(createDto);
        AuthUserEntity saveAuthUser = repository.insert(authUserEntity);
        return new ResponseEntity<>(new Data<>(saveAuthUser.getId()), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Data<AuthUserDTO>> update(AuthUserUpdateDTO updateDto) {
        validator.validUpdateDTO(updateDto);
        AuthUserEntity authUser = repository.findById(updateDto.getId()).get();
        if (Objects.isNull(authUser)) {
            return new ResponseEntity<>(new Data<>(
                    ApplicationError.builder()
                            .status(HttpStatus.NOT_FOUND)
                            .message("User was not found by id %s".formatted(updateDto.getId()))
                            .build()), HttpStatus.NOT_FOUND);
        }

        AuthUserEntity authUserUpdateEntity = mapper.fromUpdateDto(updateDto);

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


        return new ResponseEntity<>(new Data<>(true), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Data<Void>> delete(String id) {
        Optional<AuthUserEntity> authUser = repository.findById(id);
        if (authUser.isEmpty()) {
            return new ResponseEntity<>(new Data<>(
                    ApplicationError.builder()
                            .status(HttpStatus.NOT_FOUND)
                            .message("User was not found by id %s".formatted(id))
                            .build()), HttpStatus.NOT_FOUND);
        }
        repository.delete(authUser.get());
        return new ResponseEntity<>(new Data<>(true), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Data<List<AuthUserDTO>>> getAll(GenericCriteria criteria) {
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        Page<AuthUserEntity> all = repository.findAll(pageable);
        if (all.isEmpty()) {
            return new ResponseEntity<>(new Data<>(
                    ApplicationError.builder()
                            .status(HttpStatus.NOT_FOUND)
                            .message("There are not any user yet")
                            .build()), HttpStatus.NOT_FOUND);
        }
        List<AuthUserEntity> collect = all.stream().collect(Collectors.toList());
        List<AuthUserDTO> authUserDTOS = mapper.toDto(collect);
        return new ResponseEntity<>(new Data<>(authUserDTOS), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Data<AuthUserDTO>> get(String id) {
        AuthUserEntity authUser = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("User was not found by id %s".formatted(id));
        });
        AuthUserDTO authUserDTO = mapper.toDto(authUser);
        return new ResponseEntity<>(new Data<>(authUserDTO), HttpStatus.FOUND);
    }

    public ResponseEntity<Data<List<AuthUserDTO>>> getAllBlocked(GenericCriteria criteria) {
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
}
