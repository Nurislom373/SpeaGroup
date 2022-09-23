package org.khasanof.auth_service.service.auth_block;

import org.khasanof.auth_service.criteria.auth_block.AuthBlockCriteria;
import org.khasanof.auth_service.dto.auth_block.AuthBlockCreateDTO;
import org.khasanof.auth_service.dto.auth_block.AuthBlockDetailDTO;
import org.khasanof.auth_service.dto.auth_block.AuthBlockGetDTO;
import org.khasanof.auth_service.dto.auth_block.AuthBlockUpdateDTO;
import org.khasanof.auth_service.entity.auth_block.AuthBlockEntity;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.entity.blocked_for.BlockedForEntity;
import org.khasanof.auth_service.mapper.auth_block.AuthBlockMapper;
import org.khasanof.auth_service.repository.auth_block.AuthBlockRepository;
import org.khasanof.auth_service.repository.auth_user.AuthUserRepository;
import org.khasanof.auth_service.repository.blocked_for.BlockedForRepository;
import org.khasanof.auth_service.service.AbstractService;
import org.khasanof.auth_service.validator.auth_block.AuthBlockValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AuthBlockServiceImpl extends AbstractService<AuthBlockRepository, AuthBlockMapper, AuthBlockValidator> implements AuthBlockService {

    private final AuthUserRepository userRepository;
    private final BlockedForRepository blockedForRepository;

    public AuthBlockServiceImpl(AuthBlockRepository repository, AuthBlockMapper mapper, AuthBlockValidator validator, AuthUserRepository userRepository, BlockedForRepository blockedForRepository) {
        super(repository, mapper, validator);
        this.userRepository = userRepository;
        this.blockedForRepository = blockedForRepository;
    }

    @Override
    public void create(AuthBlockCreateDTO dto) {
        validator.validCreateDTO(dto);
        AuthBlockEntity authBlock = repository.findByUserId(dto.getAuthId());
        if (Objects.isNull(authBlock)) {
            AuthUserEntity userEntity = userRepository.findById(dto.getAuthId())
                    .orElseThrow(() -> {
                        throw new NotFoundException("User not found");
                    });
            BlockedForEntity blockedForEntity = blockedForRepository.findById(dto.getBlockedForId())
                    .orElseThrow(() -> {
                        throw new NotFoundException("Blocked For not found");
                    });
            repository.insert(
                    AuthBlockEntity.builder()
                            .blockedFor(blockedForEntity)
                            .userId(userEntity)
                            .duration(currentTimeAddMinute(dto.getDurationTime()))
                            .build()
            );
        } else {
            AuthBlockEntity blockEntity = repository.findByUserId(dto.getAuthId());
            blockEntity.setBlockedFor(
                    blockedForRepository.findById(dto.getBlockedForId())
                            .orElseThrow(() -> {
                                throw new NotFoundException("Blocked For not found");
                            })
            );
            blockEntity.setDuration(currentTimeAddMinute(dto.getDurationTime()));
            blockEntity.setUpdatedAt(Instant.now());
            blockEntity.setUpdatedBy("-1");
            repository.save(blockEntity);
        }
    }

    @Override
    public void update(AuthBlockUpdateDTO dto) {
        validator.validUpdateDTO(dto);
        AuthBlockEntity blockEntity = repository.findById(dto.getId()).orElseThrow(() -> {
            throw new NotFoundException("Auth Block not found");
        });
        blockEntity.setDuration(currentTimeAddMinute(dto.getDurationTime()));
        blockEntity.setBlockedFor(
                blockedForRepository.findById(dto.getBlockedForId()).orElseThrow(() -> {
                    throw new NotFoundException("Blocked For not found");
                })
        );
        blockEntity.setUpdatedAt(Instant.now());
        blockEntity.setUpdatedBy("-1");
        repository.save(blockEntity);
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id))
            throw new NotFoundException("Auth Block not found");
        repository.deleteById(id);
    }

    @Override
    public AuthBlockGetDTO get(String id) {
        validator.validKey(id);
        AuthBlockEntity authBlockEntity = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Auth Block not found");
        });
        AuthBlockGetDTO authBlockGetDTO = mapper.fromGetDTO(authBlockEntity);
        authBlockGetDTO.setAuthId(authBlockEntity.getUserId().getId());
        authBlockGetDTO.setBlockedForCode(authBlockEntity.getBlockedFor().getCode());
        return authBlockGetDTO;
    }

    @Override
    public AuthBlockDetailDTO detail(String id) {
        return mapper.fromDetailDTO(repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Auth Block not found");
        }));
    }

    @Override
    public List<AuthBlockGetDTO> list(AuthBlockCriteria criteria) {
        List<AuthBlockEntity> authBlockEntities = repository.findAll(PageRequest.of(criteria.getPage(), criteria.getSize())).
                stream().toList();
        List<AuthBlockGetDTO> list = new ArrayList<>();
        authBlockEntities.forEach((block) -> {
            list.add(new AuthBlockGetDTO(
                    block.getUserId().getId(),
                    block.getDuration(),
                    block.getBlockedFor().getCode()
            ));
        });
        return list;
    }

    private Instant currentTimeAddMinute(Integer minute) {
        return Instant.ofEpochMilli(System.currentTimeMillis() + (minute * 60 * 1000));
    }
}
