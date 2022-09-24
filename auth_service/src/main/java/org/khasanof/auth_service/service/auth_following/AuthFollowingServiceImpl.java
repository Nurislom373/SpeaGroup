package org.khasanof.auth_service.service.auth_following;

import org.khasanof.auth_service.criteria.auth_following.AuthFollowingCriteria;
import org.khasanof.auth_service.dto.auth_following.AuthFollowingCreateDTO;
import org.khasanof.auth_service.dto.auth_following.AuthFollowingDetailDTO;
import org.khasanof.auth_service.dto.auth_following.AuthFollowingGetDTO;
import org.khasanof.auth_service.entity.auth_following.AuthFollowingEntity;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.mapper.auth_following.AuthFollowingMapper;
import org.khasanof.auth_service.repository.auth_following.AuthFollowingRepository;
import org.khasanof.auth_service.repository.auth_user.AuthUserRepository;
import org.khasanof.auth_service.service.AbstractService;
import org.khasanof.auth_service.validator.auth_following.AuthFollowingValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthFollowingServiceImpl extends AbstractService<AuthFollowingRepository, AuthFollowingMapper, AuthFollowingValidator> implements AuthFollowingService {

    private final AuthUserRepository userRepository;

    public AuthFollowingServiceImpl(AuthFollowingRepository repository, AuthFollowingMapper mapper, AuthFollowingValidator validator, AuthUserRepository userRepository) {
        super(repository, mapper, validator);
        this.userRepository = userRepository;
    }

    @Override
    public void create(AuthFollowingCreateDTO dto) {
        validator.validCreateDTO(dto);
        AuthUserEntity user = userRepository.findById(dto.getAuthId())
                .orElseThrow(() -> {
                    throw new NotFoundException("User not found");
                });
        List<AuthUserEntity> list = new ArrayList<>();
        dto.getFollowingsId().forEach((following) -> {
            list.add(userRepository.
                    findById(following).orElseThrow(() -> {
                        throw new NotFoundException("User not found");
                    }));
        });
        repository.save(AuthFollowingEntity.builder()
                .userId(user)
                .followers(list)
                .build());
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id))
            throw new NotFoundException("Auth Following not found");
        else
            repository.deleteById(id);
    }

    @Override
    public AuthFollowingGetDTO get(String id) {
        validator.validKey(id);
        AuthFollowingEntity followingEntity = repository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Auth Following not found");
                });
        List<String> followingIds = followingEntity.getFollowers()
                .parallelStream()
                .map(this::getId).toList();
        return new AuthFollowingGetDTO(followingEntity.getUserId().getId(), followingIds);
    }

    @Override
    public AuthFollowingDetailDTO detail(String id) {
        validator.validKey(id);
        return mapper.fromDetailDTO(repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Auth Following not found");
        }));
    }

    @Override
    public List<AuthFollowingGetDTO> list(AuthFollowingCriteria criteria) {
        return repository.findAll(
                PageRequest.of(criteria.getPage(), criteria.getSize())
        ).stream().parallel().map(this::getDTO).toList();
    }

    private String getId(AuthUserEntity entity) {
        return entity.getId();
    }

    private AuthFollowingGetDTO getDTO(AuthFollowingEntity entity) {
        List<String> ids = entity.getFollowers()
                .parallelStream().map(this::getId).toList();
        return new AuthFollowingGetDTO(entity.getUserId().getId(), ids);
    }
}
