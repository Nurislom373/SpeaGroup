package org.khasanof.auth_service.service.auth_follower;

import org.khasanof.auth_service.criteria.auth_follower.AuthFollowerCriteria;
import org.khasanof.auth_service.dto.auth_follower.AuthFollowerCreateDTO;
import org.khasanof.auth_service.dto.auth_follower.AuthFollowerDetailDTO;
import org.khasanof.auth_service.dto.auth_follower.AuthFollowerGetDTO;
import org.khasanof.auth_service.entity.auth_follower.AuthFollowerEntity;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.mapper.auth_follower.AuthFollowerMapper;
import org.khasanof.auth_service.repository.auth_follower.AuthFollowerRepository;
import org.khasanof.auth_service.repository.auth_user.AuthUserRepository;
import org.khasanof.auth_service.service.AbstractService;
import org.khasanof.auth_service.validator.auth_follower.AuthFollowerValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthFollowerServiceImpl extends AbstractService<AuthFollowerRepository, AuthFollowerMapper, AuthFollowerValidator> implements AuthFollowerService {

    private final AuthUserRepository userRepository;

    public AuthFollowerServiceImpl(AuthFollowerRepository repository, AuthFollowerMapper mapper, AuthFollowerValidator validator, AuthUserRepository userRepository) {
        super(repository, mapper, validator);
        this.userRepository = userRepository;
    }

    @Override
    public void create(AuthFollowerCreateDTO dto) {
        validator.validCreateDTO(dto);
        AuthUserEntity user = userRepository.findById(dto.getAuthId())
                .orElseThrow(() -> {
                    throw new NotFoundException("User not found");
                });
        List<AuthUserEntity> list = new ArrayList<>();
        dto.getFollowerId().forEach((following) -> {
            list.add(userRepository.
                    findById(following).orElseThrow(() -> {
                        throw new NotFoundException("User not found");
                    }));
        });
        repository.save(AuthFollowerEntity.builder()
                .userId(user)
                .followers(list)
                .build());
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id))
            throw new NotFoundException("Auth Follower not found");
        else
            repository.deleteById(id);
    }

    @Override
    public AuthFollowerGetDTO get(String id) {
        validator.validKey(id);
        AuthFollowerEntity followerEntity = repository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Auth Following not found");
                });
        List<String> followingIds = followerEntity.getFollowers()
                .parallelStream()
                .map(this::getId).toList();
        return new AuthFollowerGetDTO(followerEntity.getUserId().getId(), followingIds);
    }

    @Override
    public AuthFollowerDetailDTO detail(String id) {
        validator.validKey(id);
        return mapper.fromDetailDTO(repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Auth Following not found");
        }));
    }

    @Override
    public List<AuthFollowerGetDTO> list(AuthFollowerCriteria criteria) {
        return repository.findAll(
                PageRequest.of(criteria.getPage(), criteria.getSize())
        ).stream().parallel().map(this::getDTO).toList();
    }

    private String getId(AuthUserEntity entity) {
        return entity.getId();
    }

    private AuthFollowerGetDTO getDTO(AuthFollowerEntity entity) {
        List<String> ids = entity.getFollowers()
                .parallelStream()
                .map(this::getId).toList();
        return new AuthFollowerGetDTO(entity.getUserId().getId(), ids);
    }
}
