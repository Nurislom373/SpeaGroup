package org.khasanof.auth_service.service.auth_token;

import org.khasanof.auth_service.criteria.auth_token.AuthTokenCriteria;
import org.khasanof.auth_service.dto.auth_token.AuthTokenCreateDTO;
import org.khasanof.auth_service.dto.auth_token.AuthTokenDetailDTO;
import org.khasanof.auth_service.dto.auth_token.AuthTokenGetDTO;
import org.khasanof.auth_service.dto.auth_token.AuthTokenUpdateDTO;
import org.khasanof.auth_service.entity.auth_token.AuthTokenEntity;
import org.khasanof.auth_service.mapper.auth_token.AuthTokenMapper;
import org.khasanof.auth_service.repository.auth_token.AuthTokenRepository;
import org.khasanof.auth_service.service.AbstractService;
import org.khasanof.auth_service.validator.auth_token.AuthTokenValidator;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AuthTokenServiceImpl extends AbstractService<AuthTokenRepository, AuthTokenMapper, AuthTokenValidator> implements AuthTokenService {

    public AuthTokenServiceImpl(AuthTokenRepository repository, AuthTokenMapper mapper, AuthTokenValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public void create(AuthTokenCreateDTO dto) {
        validator.validCreateDTO(dto);
        AuthTokenEntity authTokenEntity = mapper.toCreateDTO(dto);
        authTokenEntity.setDuration(changeIntegerMinToTime(dto.getMinTime()));
        repository.save(authTokenEntity);
    }

    @Override
    public void update(AuthTokenUpdateDTO dto) {

    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        repository.deleteById(id);
    }

    @Override
    public AuthTokenGetDTO get(String id) {
        return null;
    }

    @Override
    public AuthTokenDetailDTO detail(String id) {
        return null;
    }

    @Override
    public List<AuthTokenGetDTO> list(AuthTokenCriteria criteria) {
        return null;
    }

    private Instant changeIntegerMinToTime(Integer minTime) {
        Instant now = Instant.now();
        return now.plusNanos(TimeUnit.MINUTES.toNanos(minTime));
    }
}
