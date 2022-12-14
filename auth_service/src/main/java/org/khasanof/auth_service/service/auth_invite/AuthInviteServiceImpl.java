package org.khasanof.auth_service.service.auth_invite;

import org.khasanof.auth_service.criteria.auth_invite.AuthInviteCriteria;
import org.khasanof.auth_service.dto.auth_invite.AuthInviteChangeStatusDTO;
import org.khasanof.auth_service.dto.auth_invite.AuthInviteCreateDTO;
import org.khasanof.auth_service.dto.auth_invite.AuthInviteDetailDTO;
import org.khasanof.auth_service.dto.auth_invite.AuthInviteGetDTO;
import org.khasanof.auth_service.entity.auth_info.AuthInfoEntity;
import org.khasanof.auth_service.entity.auth_invite.AuthInviteEntity;
import org.khasanof.auth_service.entity.invite.InviteEntity;
import org.khasanof.auth_service.enums.auth_info.AuthInfoVisibilityEnum;
import org.khasanof.auth_service.enums.auth_invite.AuthInviteStatusEnum;
import org.khasanof.auth_service.exception.exceptions.InvalidValidationException;
import org.khasanof.auth_service.mapper.auth_invite.AuthInviteMapper;
import org.khasanof.auth_service.repository.auth_invite.AuthInviteRepository;
import org.khasanof.auth_service.service.AbstractService;
import org.khasanof.auth_service.service.auth_info.AuthInfoService;
import org.khasanof.auth_service.service.auth_user.AuthUserService;
import org.khasanof.auth_service.validator.auth_invite.AuthInviteValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class AuthInviteServiceImpl extends AbstractService<AuthInviteRepository, AuthInviteMapper, AuthInviteValidator> implements AuthInviteService {

    private final AuthInfoService authInfoService;
    private final AuthUserService userService;

    public AuthInviteServiceImpl(AuthInviteRepository repository, AuthInviteMapper mapper, AuthInviteValidator validator, AuthInfoService authInfoService, AuthUserService userService) {
        super(repository, mapper, validator);
        this.authInfoService = authInfoService;
        this.userService = userService;
    }

    @Override
    public void create(AuthInviteCreateDTO dto) {
        validator.validCreateDTO(dto);
        AuthInfoEntity infoEntity = authInfoService.getByUserId(dto.getInviteUserId());
        if (!AuthInfoVisibilityEnum.PRIVATE.equals(infoEntity.getVisibility())) {
            throw new InvalidValidationException("User Visibility is not PRIVATE!");
        }
        AuthInviteEntity authInvite = repository.findByUserIdEquals(infoEntity.getUserId())
                .orElseThrow(() -> new NotFoundException("User Invites not found"));
        LinkedList<InviteEntity> invites = authInvite.getInvites();
        if (Objects.isNull(invites)) {
            invites = new LinkedList<>();
        }
        boolean anyMatch = false;
        if (invites.size() >= 1) {
            anyMatch = invites.stream()
                    .anyMatch(any -> any.getUserId().equals(dto.getRequestUserId()));
        }
        if (!anyMatch) {
            invites.add(new InviteEntity(dto.getRequestUserId(), AuthInviteStatusEnum.PENDING));
            authInvite.setInvites(invites);
            authInvite.setUpdatedAt(Instant.now());
            authInvite.setUpdatedBy("-1");
            repository.save(authInvite);
        }
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id)) {
            throw new NotFoundException("User Invites not found");
        }
        repository.deleteById(id);
    }

    @Override
    public void inviteSelect(AuthInviteChangeStatusDTO dto) {
        validator.validChangeStatusDTO(dto);
        AuthInviteEntity inviteEntity = repository.findById(dto.getId())
                .orElseThrow(() -> {
                    throw new NotFoundException("User Invites not found");
                });
        LinkedList<InviteEntity> invites = inviteEntity.getInvites();
        InviteEntity entity = invites.stream()
                .filter(f -> f.getUserId().equals(dto.getRequestUserId()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Invite not found"));
        invites.remove(entity);
        entity.setStatus(dto.getStatus());
        if (AuthInviteStatusEnum.ACCEPT.equals(dto.getStatus())) {
            entity.setAcceptTime(Instant.now());
        }
        invites.add(entity);
        inviteEntity.setInvites(invites);
        inviteEntity.setUpdatedAt(Instant.now());
        inviteEntity.setUpdatedBy(inviteEntity.getUserId().getId());
        repository.save(inviteEntity);
    }

    @Override
    public void deleteRequest(String id, String userId) {
        validator.validKey(id);
        validator.validKey(userId);
        AuthInviteEntity authInvite = repository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("User Invites not found");
                });
        LinkedList<InviteEntity> invites = authInvite.getInvites();
        if (!invites.removeIf(f -> f.getUserId().equals(userId))) {
            throw new NotFoundException("Request User not found");
        }
        authInvite.setInvites(invites);
        authInvite.setUpdatedAt(Instant.now());
        authInvite.setUpdatedBy(authInvite.getUserId().getId());
        repository.save(authInvite);
    }

    @Override
    public AuthInviteGetDTO get(String id) {
        validator.validKey(id);
        return returnToGetDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("User Invites not found");
                        })
        );
    }

    @Override
    public AuthInviteDetailDTO detail(String id) {
        validator.validKey(id);
        return mapper.fromDetailDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("User Invites not found");
                        })
        );
    }

    @Override
    public List<AuthInviteGetDTO> list(AuthInviteCriteria criteria) {
        return repository.findAll(
                        PageRequest.of(criteria.getPage(), criteria.getSize())
                ).stream()
                .map(this::returnToGetDTO)
                .toList();
    }

    private AuthInviteGetDTO returnToGetDTO(AuthInviteEntity entity) {
        AuthInviteGetDTO authInviteGetDTO = mapper.fromGetDTO(entity);
        authInviteGetDTO.setAuthUserId(entity.getUserId().getId());
        List<String> ids = entity.getInvites().stream().map(InviteEntity::getUserId).toList();
        authInviteGetDTO.setInviteIds(ids);
        return authInviteGetDTO;
    }
}
