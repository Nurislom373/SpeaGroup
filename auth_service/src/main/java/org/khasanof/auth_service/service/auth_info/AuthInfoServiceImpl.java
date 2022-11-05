package org.khasanof.auth_service.service.auth_info;

import org.khasanof.auth_service.criteria.auth_info.AuthInfoBetweenCriteria;
import org.khasanof.auth_service.criteria.auth_info.AuthInfoCriteria;
import org.khasanof.auth_service.criteria.auth_info.AuthInfoSearchCriteria;
import org.khasanof.auth_service.dto.auth_info.*;
import org.khasanof.auth_service.dto.category.*;
import org.khasanof.auth_service.dto.location.LocationCreateDTO;
import org.khasanof.auth_service.dto.location.LocationUpdateDTO;
import org.khasanof.auth_service.entity.auth_info.AuthInfoEntity;
import org.khasanof.auth_service.entity.auth_invite.AuthInviteEntity;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.entity.location.LocationEntity;
import org.khasanof.auth_service.enums.auth_info.AuthInfoVisibilityEnum;
import org.khasanof.auth_service.exception.exceptions.AlreadyCreatedException;
import org.khasanof.auth_service.exception.exceptions.ClientResponseException;
import org.khasanof.auth_service.exception.exceptions.InvalidValidationException;
import org.khasanof.auth_service.mapper.auth_info.AuthInfoMapper;
import org.khasanof.auth_service.predicate.auth_info.AuthInfoPredicateExecutor;
import org.khasanof.auth_service.repository.auth_info.AuthInfoRepository;
import org.khasanof.auth_service.repository.auth_invite.AuthInviteRepository;
import org.khasanof.auth_service.repository.auth_user.AuthUserRepository;
import org.khasanof.auth_service.service.AbstractService;
import org.khasanof.auth_service.service.auth_user.AuthUserService;
import org.khasanof.auth_service.validator.auth_info.AuthInfoValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

@Service
public class AuthInfoServiceImpl extends AbstractService<AuthInfoRepository, AuthInfoMapper, AuthInfoValidator> implements AuthInfoService {

    private final AuthUserService authUserService;
    private final AuthInviteRepository inviteRepository;
    private final PostCategoryFeignClient categoryFeignClient;
    private final MongoTemplate mongoTemplate;

    public AuthInfoServiceImpl(AuthInfoRepository repository, AuthInfoMapper mapper, AuthInfoValidator validator, AuthUserService authUserService, AuthInviteRepository inviteRepository, PostCategoryFeignClient categoryFeignClient, MongoTemplate mongoTemplate) {
        super(repository, mapper, validator);
        this.authUserService = authUserService;
        this.inviteRepository = inviteRepository;
        this.categoryFeignClient = categoryFeignClient;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void create(AuthInfoCreateDTO dto) {
        validator.validCreateDTO(dto);
        AuthUserEntity entity = authUserService.getEntity(dto.getAuthId());
        if (repository.existsByUserIdEquals(entity)) {
            throw new AlreadyCreatedException("User Info Already Created!");
        }
        try {
            List<String> ids = new ArrayList<>(new HashSet<>(dto.getInterestsId()));
            List<CategoryDetailDTO> dtoList = categoryFeignClient.findAllById(
                            new CategoryFindAllRequestDTO(ids))
                    .getData();
            List<String> list = dtoList.stream()
                    .map(CategoryDetailDTO::getId)
                    .toList();
            AuthInfoEntity authInfoEntity = mapper.toCreateDTO(dto);
            authInfoEntity.setUserId(entity);
            authInfoEntity.setVisibility(AuthInfoVisibilityEnum.PUBLIC);
            authInfoEntity.setInterests(list);
            repository.save(authInfoEntity);
        } catch (Exception e) {
            throw new ClientResponseException("Client Response Exception!");
        }
    }

    @Override
    public void update(AuthInfoUpdateDTO dto) {
        validator.validUpdateDTO(dto);
        AuthInfoEntity entity = repository.findById(dto.getId())
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                });
        entity.setUpdatedAt(Instant.now());
        entity.setUpdatedBy(entity.getUserId().getId());
        BeanUtils.copyProperties(dto, entity);
        repository.save(entity);
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (repository.existsById(id))
            repository.deleteById(id);
        else
            throw new NotFoundException("Info not found");
    }

    @Override
    public AuthInfoGetDTO get(String id) {
        validator.validKey(id);
        return returnToGetDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Info not found");
                        }));
    }

    @Override
    public AuthInfoDetailDTO detail(String id) {
        validator.validKey(id);
        return mapper.fromDetailDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Info not found");
                        }));
    }

    @Override
    public List<AuthInfoGetDTO> list(AuthInfoCriteria criteria) {
        return repository.findAll(
                        PageRequest.of(
                                criteria.getPage(),
                                criteria.getSize(),
                                criteria.getDirection(),
                                criteria.getFieldsEnum().getValue()
                        )).stream()
                .map(this::returnToGetDTO)
                .toList();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<AuthInfoGetDTO> listWithSc(AuthInfoSearchCriteria searchCriteria) {
        return mongoTemplate.find(
                        new AuthInfoPredicateExecutor.SearchPredicate(searchCriteria).searchQuery(),
                        AuthInfoEntity.class).stream()
                .map(this::returnToGetDTO).toList();
    }

    @Override
    public List<AuthInfoGetDTO> listWithBc(AuthInfoBetweenCriteria betweenCriteria) {
        return mongoTemplate.find(
                        new AuthInfoPredicateExecutor.BetweenPredicate(betweenCriteria).betweenQuery(),
                        AuthInfoEntity.class).stream()
                .map(this::returnToGetDTO).toList();
    }

    @Override
    public void addLocation(LocationCreateDTO dto) {
        validator.validLocationCreateDTO(dto);
        AuthInfoEntity info = repository.findById(dto.getInfoId())
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                });
        if (Objects.nonNull(info.getLocation())) {
            throw new AlreadyCreatedException("Location is Already Created!");
        }
        LocationEntity location = new LocationEntity();
        BeanUtils.copyProperties(dto, location);
        info.setLocation(location);
        info.setUpdatedBy(info.getUserId().getId());
        info.setUpdatedAt(Instant.now());
        repository.save(info);
    }

    @Override
    public void updateLocation(LocationUpdateDTO dto) {
        validator.validLocationUpdateDTO(dto);
        AuthInfoEntity info = repository.findById(dto.getInfoId())
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                });
        LocationEntity location = new LocationEntity();
        BeanUtils.copyProperties(dto, location);
        location.setUpdatedAt(Instant.now());
        location.setUpdatedBy(info.getUserId().getId());
        info.setLocation(location);
        info.setUpdatedAt(Instant.now());
        info.setUpdatedBy(info.getUserId().getId());
        repository.save(info);
    }

    @Override
    public void deleteLocation(String infoId) {
        validator.validKey(infoId);
        AuthInfoEntity info = repository.findById(infoId)
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                });
        info.setLocation(null);
        info.setUpdatedAt(Instant.now());
        info.setUpdatedBy(info.getUserId().getId());
        repository.save(info);
    }

    @Override
    public void addCategory(CategoryAddDTO dto) {
        validator.validKey(dto.getInfoId());
        validator.validKey(dto.getCategoryId());
        AuthInfoEntity entity = repository.findById(dto.getInfoId())
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                });
        List<String> interests = entity.getInterests();
        boolean anyMatch = interests.stream()
                .anyMatch(any -> any.equals(dto.getCategoryId()));
        if (!anyMatch) {
            try {
                CategoryDetailDTO data = categoryFeignClient.get(dto.getCategoryId()).getData();
                entity.getInterests().add(data.getId());
                repository.save(entity);
            } catch (Exception e) {
                throw new ClientResponseException("Client Response Exception!");
            }
        }
    }

    @Override
    public void addAllCategory(CategoryAddAllDTO dto) {
        validator.validKey(dto.getInfoId());
        validator.validKeys(dto.getCategories());
        AuthInfoEntity entity = repository.findById(dto.getInfoId())
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                });
        List<String> interests = entity.getInterests();
        List<String> removes = new ArrayList<>();
        List<String> dtoCategories = new ArrayList<>(new HashSet<>(dto.getCategories()));
        dtoCategories.forEach((elem) -> {
            boolean anyMatch = interests.stream()
                    .anyMatch(any -> any.equals(elem));
            if (anyMatch) {
                removes.add(elem);
            }
        });
        if (removes.size() >= 1) {
            dtoCategories.removeAll(removes);
        }
        if (dtoCategories.size() >= 1) {
            try {
                findAndAdd(dto, entity);
            } catch (Exception e) {
                throw new ClientResponseException("Client Respone Exception!");
            }
        }
    }

    private void findAndAdd(CategoryAddAllDTO dto, AuthInfoEntity entity) throws Exception {
        List<CategoryDetailDTO> data = categoryFeignClient.findAllById(
                        new CategoryFindAllRequestDTO(dto.getCategories()))
                .getData();
        List<String> ids = data.stream()
                .map(CategoryDetailDTO::getId)
                .toList();
        entity.getInterests().addAll(ids);
        entity.setUpdatedAt(Instant.now());
        entity.setUpdatedBy(entity.getUserId().getId());
        repository.save(entity);
    }

    @Override
    public void deleteCategory(CategoryDeleteDTO dto) {
        validator.validKey(dto.getInfoId());
        validator.validKey(dto.getCategoryId());
        AuthInfoEntity entity = repository.findById(dto.getInfoId())
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                });
        boolean removeIf = entity.getInterests()
                .removeIf(obj -> obj.equals(dto.getCategoryId()));
        if (!removeIf)
            throw new RuntimeException("Category not found");
        entity.setUpdatedAt(Instant.now());
        entity.setUpdatedBy(entity.getUserId().getId());
        repository.save(entity);
    }

    @Override
    public void deleteAllCategory(CategoryDeleteAllDTO dto) {
        validator.validKey(dto.getInfoId());
        validator.validKeys(dto.getCategories());
        AuthInfoEntity entity = repository.findById(dto.getInfoId())
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                });
        List<String> ctgs = new ArrayList<>(new HashSet<>(dto.getCategories()));
        ctgs.forEach((id) -> {
            entity.getInterests()
                    .removeIf(obj -> obj.equals(id));
        });
        entity.setUpdatedAt(Instant.now());
        entity.setUpdatedBy(entity.getUserId().getId());
        repository.save(entity);
    }

    @Override
    public CategoryDetailDTO getCategory(String infoId, String categoryId) {
        validator.validKey(infoId);
        validator.validKey(categoryId);
        try {
            return categoryFeignClient.get(
                            repository.findById(infoId)
                                    .orElseThrow(() -> {
                                        throw new NotFoundException("Info not found");
                                    }).getId())
                    .getData();
        } catch (Exception e) {
            throw new ClientResponseException(e.getMessage());
        }
    }

    @Override
    public List<CategoryDetailDTO> listCategory(String infoId) {
        validator.validKey(infoId);
        try {
            return categoryFeignClient.findAllById(
                    new CategoryFindAllRequestDTO(
                            repository.findById(infoId)
                                    .orElseThrow(() -> {
                                        throw new NotFoundException("Info not found");
                                    }).getInterests())
            ).getData();
        } catch (Exception e) {
            throw new ClientResponseException("Client Response Exception");
        }
    }

    @Override
    public int categoriesCount(String infoId) {
        validator.validKey(infoId);
        return repository.findById(infoId)
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                }).getInterests()
                .size();
    }

    @Override
    public AuthInfoEntity getByUserId(String id) {
        validator.validKey(id);
        AuthInfoEntity entity = mongoTemplate.findOne(
                Query.query(new Criteria("userId")
                        .is(authUserService.getEntity(id))),
                AuthInfoEntity.class);
        if (Objects.isNull(entity)) {
            throw new InvalidValidationException("Auth Info not found");
        }
        return entity;
    }

    @Override
    public void changeVisibility(AuthInfoChangeVisibilityDTO dto) {
        validator.validChangeVisibility(dto);
        AuthInfoEntity authInfo = repository.findById(dto.getId())
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                });
        authInfo.setVisibility(dto.getVisibility());
        authInfo.setUpdatedAt(Instant.now());
        authInfo.setUpdatedBy(authInfo.getUserId().getId());
        repository.save(authInfo);
        if (AuthInfoVisibilityEnum.PRIVATE.equals(dto.getVisibility()))
            inviteRepository.save(new AuthInviteEntity(authUserService.getEntity(authInfo.getUserId().getId())));
    }

    @Override
    public void deleteByUserId(String id) {
        validator.validKey(id);
        repository.deleteByUserId(authUserService.getEntity(id));
    }

    private AuthInfoGetDTO returnToGetDTO(AuthInfoEntity entity) {
        AuthInfoGetDTO authInfoGetDTO = mapper.fromGetDTO(entity);
        authInfoGetDTO.setAuthId(entity.getUserId().getId());
        return authInfoGetDTO;
    }

    private Date strParseToDate(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
