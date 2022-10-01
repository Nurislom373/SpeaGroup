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
import org.khasanof.auth_service.utils.BaseUtils;
import org.khasanof.auth_service.validator.auth_block.AuthBlockValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

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
                            .duration(BaseUtils.currentTimeAddMinute(dto.getDurationTime()))
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
            blockEntity.setDuration(BaseUtils.currentTimeAddMinute(dto.getDurationTime()));
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
        if (!Instant.now().isAfter(blockEntity.getDuration())) {
            blockEntity.setDuration(BaseUtils.currentTimeAddMinute(dto.getDurationTime() + minusToNow(blockEntity.getDuration())));
        } else {
            blockEntity.setDuration(BaseUtils.currentTimeAddMinute(dto.getDurationTime()));
        }
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

    private Integer minusToNow(Instant time) {
        String now = Instant.now().toString().substring(11, 19);
        String blockTime = time.toString().substring(11, 19);
        List<String> nowTimeList = getTokensWithCollection(now, ":", false);
        List<String> blockTimeList = getTokensWithCollection(blockTime, ":", false);
        for (int i = 0; i <= 3; i++) {
            if (Float.parseFloat(nowTimeList.get(i)) == Float.parseFloat(blockTimeList.get(i))) {
                continue;
            } else if (Float.parseFloat(nowTimeList.get(i)) < Float.parseFloat(blockTimeList.get(i))) {
                return Integer.parseInt(blockTimeList.get(i)) - Integer.parseInt(nowTimeList.get(i));
            }
            return 0;
        }
        return null;
    }

    public static List<String> getTokensWithCollection(String var, String delim, boolean returnDelim) {
        return Collections.list(new StringTokenizer(var, delim, returnDelim))
                .stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());
    }
}
