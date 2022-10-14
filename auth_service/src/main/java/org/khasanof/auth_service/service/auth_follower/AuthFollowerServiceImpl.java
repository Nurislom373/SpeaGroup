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
public class AuthFollowerServiceImpl extends AbstractService<AuthFollowerRepository, AuthFollowerMapper, AuthFollowerValidator> implements AuthFollowerService {

    private final AuthUserRepository userRepository;
    private final MongoTemplate mongoTemplate;

    public AuthFollowerServiceImpl(AuthFollowerRepository repository, AuthFollowerMapper mapper, AuthFollowerValidator validator, AuthUserRepository userRepository, MongoTemplate mongoTemplate) {
        super(repository, mapper, validator);
        this.userRepository = userRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void create(AuthFollowerCreateDTO dto) {
        validator.validCreateDTO(dto);
        AuthUserEntity user = userRepository.findById(dto.getAuthId())
                .orElseThrow(() -> {
                    throw new NotFoundException("User not found");
                });
        AuthFollowerEntity followerEntity = mongoTemplate.findOne(
                Query.query(new Criteria("userId").is(user)),
                AuthFollowerEntity.class);
        if (Objects.nonNull(followerEntity)) {
            List<String> followerId = dto.getFollowerId();
            List<AuthUserEntity> followers = followerEntity.getFollowers();
            List<String> alreadyFollowers = new ArrayList<>();
            for (String fol : followerId) {
                alreadyFollowers.add(
                        followers.stream()
                                .filter(f -> f.getId().equals(fol))
                                .map(this::getId)
                                .findFirst()
                                .orElse(null)
                );
            }
            if (alreadyFollowers.size() != 0) {
                followerId.removeAll(alreadyFollowers);
            }
            if (followerId.size() != 0) {
                List<AuthUserEntity> followers1 = followerEntity.getFollowers();
                List<AuthUserEntity> userEntities = followerId.stream()
                        .map(userRepository::findById)
                        .map(Optional::orElseThrow)
                        .toList();
                followers1.addAll(userEntities);
                followerEntity.setFollowers(followers);
                followerEntity.setUpdatedAt(Instant.now());
                followerEntity.setUpdatedBy(dto.getAuthId());
                repository.save(followerEntity);
            }
        } else {
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
    public void deleteFollower(String id, String userId) {
        validator.validKey(id);
        validator.validKey(userId);
        AuthFollowerEntity authFollower = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("User Follower not found");
        });
        List<AuthUserEntity> followers = authFollower.getFollowers();
        if (!followers.removeIf(f -> f.getId().equals(userId))) {
            throw new NotFoundException("User not found");
        }
        authFollower.setFollowers(followers);
        repository.save(authFollower);
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
        return mapper.fromDetailDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Auth Following not found");
                        })
        );
    }

    @Override
    public List<AuthFollowerGetDTO> list(AuthFollowerCriteria criteria) {
        return repository.findAll(
                        PageRequest.of(criteria.getPage(), criteria.getSize())
                ).stream()
                .parallel()
                .map(this::getDTO)
                .toList();
    }

    private String getId(AuthUserEntity entity) {
        return entity.getId();
    }

    private AuthFollowerGetDTO getDTO(AuthFollowerEntity entity) {
        List<String> ids = entity.getFollowers()
                .parallelStream()
                .map(this::getId)
                .toList();
        return new AuthFollowerGetDTO(entity.getUserId().getId(), ids);
    }
}
