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
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthFollowingServiceImpl extends AbstractService<AuthFollowingRepository, AuthFollowingMapper, AuthFollowingValidator> implements AuthFollowingService {

    private final AuthUserRepository userRepository;
    private final MongoTemplate mongoTemplate;

    public AuthFollowingServiceImpl(AuthFollowingRepository repository, AuthFollowingMapper mapper, AuthFollowingValidator validator, AuthUserRepository userRepository, MongoTemplate mongoTemplate) {
        super(repository, mapper, validator);
        this.userRepository = userRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void create(AuthFollowingCreateDTO dto) {
        validator.validCreateDTO(dto);
        AuthUserEntity user = userRepository.findById(dto.getAuthId())
                .orElseThrow(() -> {
                    throw new NotFoundException("User not found");
                });
        AuthFollowingEntity followingEntity = mongoTemplate.findOne(
                Query.query(new Criteria("userId").is(user)),
                AuthFollowingEntity.class);
        if (Objects.nonNull(followingEntity)) {
            List<String> newFollowingsId = dto.getFollowingsId();
            List<AuthUserEntity> followers = followingEntity.getFollowers();
            List<String> alreadyFollowings = new ArrayList<>();
            for (String newFol : newFollowingsId) {
                alreadyFollowings.add(
                        followers.stream()
                                .filter(f -> f.getId().equals(newFol))
                                .map(this::getId)
                                .findFirst()
                                .orElse(null)
                );
            }
            if (alreadyFollowings.size() != 0) {
                newFollowingsId.removeAll(alreadyFollowings);
            }
            if (newFollowingsId.size() != 0) {
                List<AuthUserEntity> followers1 = followingEntity.getFollowers();
                List<AuthUserEntity> userEntities = newFollowingsId.stream()
                        .map(userRepository::findById)
                        .map(Optional::orElseThrow)
                        .toList();
                followers1.addAll(userEntities);
                followingEntity.setUpdatedAt(Instant.now());
                followingEntity.setUpdatedBy(dto.getAuthId());
                repository.save(followingEntity);
            }
        } else {
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
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id)) {

            throw new NotFoundException("Auth Following not found");
        }
        repository.deleteById(id);
    }

    @Override
    public void deleteFollowing(String id, String userId) {
        validator.validKey(id);
        validator.validKey(userId);
        AuthFollowingEntity authFollowing = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("User Following not found");
        });
        List<AuthUserEntity> followers = authFollowing.getFollowers();
        if (!followers.removeIf(f -> f.getId().equals(userId))) {
            throw new NotFoundException("User not found");
        }
        authFollowing.setFollowers(followers);
        repository.save(authFollowing);
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
