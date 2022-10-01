package org.khasanof.auth_service.service.auth_token;

import org.khasanof.auth_service.criteria.auth_token.AuthTokenCriteria;
import org.khasanof.auth_service.dto.auth_token.AuthTokenCreateDTO;
import org.khasanof.auth_service.dto.auth_token.AuthTokenDetailDTO;
import org.khasanof.auth_service.dto.auth_token.AuthTokenGetDTO;
import org.khasanof.auth_service.dto.auth_token.AuthTokenUpdateDTO;
import org.khasanof.auth_service.entity.auth_token.AuthTokenEntity;
import org.khasanof.auth_service.mapper.auth_token.AuthTokenMapper;
import org.khasanof.auth_service.repository.auth_token.AuthTokenRepository;
import org.khasanof.auth_service.repository.auth_user.AuthUserRepository;
import org.khasanof.auth_service.service.AbstractService;
import org.khasanof.auth_service.validator.auth_token.AuthTokenValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AuthTokenServiceImpl extends AbstractService<AuthTokenRepository, AuthTokenMapper, AuthTokenValidator> implements AuthTokenService {

    private final AuthUserRepository userRepository;

    public AuthTokenServiceImpl(AuthTokenRepository repository, AuthTokenMapper mapper, AuthTokenValidator validator, AuthUserRepository userRepository) {
        super(repository, mapper, validator);
        this.userRepository = userRepository;
    }

    @Override
    public void create(AuthTokenCreateDTO dto) {
        validator.validCreateDTO(dto);
        AuthTokenEntity authTokenEntity = mapper.toCreateDTO(dto);
        authTokenEntity.setUserId(userRepository.findById(dto.getAuthId()).orElseThrow(() -> {
            throw new NotFoundException("User not found");
        }));
        authTokenEntity.setDuration(changeIntegerMinToTime(dto.getMinTime()));
        repository.save(authTokenEntity);
    }

    @Override
    public void update(AuthTokenUpdateDTO dto) {
        validator.validUpdateDTO(dto);
        AuthTokenEntity token = repository.findById(dto.getId()).orElseThrow(() -> {
            throw new NotFoundException("Token not found");
        });
        BeanUtils.copyProperties(dto, token, "id");
        repository.save(token);
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
    public AuthTokenGetDTO get(String id) {
        validator.validKey(id);
        return mapper.fromGetDTO(repository.findById(id).orElseThrow(() -> new NotFoundException("token not found")));
    }

    @Override
    public AuthTokenDetailDTO detail(String id) {
        validator.validKey(id);
        AuthTokenEntity token = repository.findById(id).orElseThrow(() -> new NotFoundException("token not found"));
        AuthTokenDetailDTO authTokenDetailDTO = mapper.fromDetailDTO(token);
        authTokenDetailDTO.setUser(token.getUserId());
        return authTokenDetailDTO;
    }

    @Override
    public List<AuthTokenGetDTO> list(AuthTokenCriteria criteria) {
        PageRequest pageRequest = PageRequest.of(criteria.getPage(), criteria.getSize(), criteria.getSort(), criteria.getFields().getValue());
        return mapper.fromGetListDTO(repository.findAll(pageRequest).toList());
    }

    @Override
    public long count() {
        return repository.count();
    }

    private Instant changeIntegerMinToTime(Integer minTime) {
        Instant now = Instant.now();
        return now.plusNanos(TimeUnit.MINUTES.toNanos(minTime));
    }

}
