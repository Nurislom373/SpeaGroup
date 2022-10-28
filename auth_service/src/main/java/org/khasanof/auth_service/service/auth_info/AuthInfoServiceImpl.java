package org.khasanof.auth_service.service.auth_info;

import org.khasanof.auth_service.criteria.auth_info.AuthInfoBetweenCriteria;
import org.khasanof.auth_service.criteria.auth_info.AuthInfoCriteria;
import org.khasanof.auth_service.criteria.auth_info.AuthInfoSearchCriteria;
import org.khasanof.auth_service.dto.auth_info.*;
import org.khasanof.auth_service.dto.category.CategoryFindAllRequestDTO;
import org.khasanof.auth_service.dto.category.CategoryGetDTO;
import org.khasanof.auth_service.dto.location.LocationCreateDTO;
import org.khasanof.auth_service.dto.location.LocationUpdateDTO;
import org.khasanof.auth_service.entity.auth_info.AuthInfoEntity;
import org.khasanof.auth_service.entity.auth_invite.AuthInviteEntity;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.entity.location.LocationEntity;
import org.khasanof.auth_service.enums.auth_info.AuthInfoVisibilityEnum;
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
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class AuthInfoServiceImpl extends AbstractService<AuthInfoRepository, AuthInfoMapper, AuthInfoValidator> implements AuthInfoService {

    private final AuthUserRepository userRepository;
    private final AuthUserService authUserService;
    private final AuthInviteRepository inviteRepository;
    private final PostCategoryFeignClient categoryFeignClient;
    private final MongoTemplate mongoTemplate;

    public AuthInfoServiceImpl(AuthInfoRepository repository, AuthInfoMapper mapper, AuthInfoValidator validator, AuthUserRepository userRepository, AuthUserService authUserService, AuthInviteRepository inviteRepository, PostCategoryFeignClient categoryFeignClient, MongoTemplate mongoTemplate) {
        super(repository, mapper, validator);
        this.userRepository = userRepository;
        this.authUserService = authUserService;
        this.inviteRepository = inviteRepository;
        this.categoryFeignClient = categoryFeignClient;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void create(AuthInfoCreateDTO dto) {
        validator.validCreateDTO(dto);
        AuthUserEntity userEntity = userRepository.findById(dto.getAuthid())
                .orElseThrow(() -> {
                    throw new NotFoundException("User not found");
                });
        List<CategoryGetDTO> dtoList = categoryFeignClient.findAllById(
                        new CategoryFindAllRequestDTO(dto.getInterestsId()))
                .getData();
        List<String> list = dtoList.stream()
                .map(CategoryGetDTO::getId)
                .toList();
        AuthInfoEntity authInfoEntity = mapper.toCreateDTO(dto);
        authInfoEntity.setUserId(userEntity);
        authInfoEntity.setBornYear(Objects.isNull(dto.getBornYearStr()) ? null : strParseToDate(dto.getBornYearStr()));
        authInfoEntity.setInterests(list);
        repository.save(authInfoEntity);
    }

    @Override
    public void update(AuthInfoUpdateDTO dto) {
        validator.validUpdateDTO(dto);
        AuthInfoEntity entity = repository.findById(dto.getId())
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                });
        if (Objects.nonNull(dto.getInterestsId())) {
            List<CategoryGetDTO> list = categoryFeignClient.findAllById(
                            new CategoryFindAllRequestDTO(dto.getInterestsId()))
                    .getData();
            List<String> ids = list.stream()
                    .map(CategoryGetDTO::getId)
                    .toList();
            entity.setInterests(ids);
        }
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
        LocationEntity location = new LocationEntity();
        BeanUtils.copyProperties(dto, location);
        info.setLocation(location);
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
        info.setLocation(location);
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
        repository.save(info);
    }

    @Override
    public void addCategory(String infoId, String categoryId) {
        validator.validKey(infoId);
        validator.validKey(categoryId);
        AuthInfoEntity entity = repository.findById(infoId)
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                });
        CategoryGetDTO data = categoryFeignClient.get(categoryId).getData();
        entity.getInterests().add(data.getId());
        repository.save(entity);
    }

    @Override
    public void addAllCategory(String infoId, List<String> categoryIds) {
        validator.validKey(infoId);
        validator.validKeys(categoryIds);
        AuthInfoEntity entity = repository.findById(infoId)
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                });
        List<CategoryGetDTO> data = categoryFeignClient.findAllById(
                        new CategoryFindAllRequestDTO(categoryIds))
                .getData();
        List<String> ids = data.stream().map(CategoryGetDTO::getId).toList();
        entity.setInterests(ids);
        repository.save(entity);
    }

    @Override
    public void deleteCategory(String infoId, String categoryId) {
        validator.validKey(infoId);
        validator.validKey(categoryId);
        AuthInfoEntity entity = repository.findById(infoId)
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                });
        boolean removeIf = entity.getInterests()
                .removeIf(obj -> obj.equals(categoryId));
        if (!removeIf)
            throw new RuntimeException("Category not found");
        repository.save(entity);
    }

    @Override
    public void deleteAllCategory(String infoId, List<String> categoryIds) {
        validator.validKey(infoId);
        validator.validKeys(categoryIds);
        AuthInfoEntity entity = repository.findById(infoId)
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                });
        categoryIds.forEach((id) -> {
            entity.getInterests()
                    .removeIf(obj -> obj.equals(id));
        });
        repository.save(entity);
    }

    @Override
    public CategoryGetDTO getCategory(String infoId, String categoryId) {
        validator.validKey(infoId);
        validator.validKey(categoryId);
        return categoryFeignClient.get(
                        repository.findById(infoId)
                                .orElseThrow(() -> {
                                    throw new NotFoundException("Info not found");
                                }).getId())
                .getData();
    }

    @Override
    public List<CategoryGetDTO> listCategory(String infoId) {
        validator.validKey(infoId);
        return categoryFeignClient.findAllById(
                new CategoryFindAllRequestDTO(
                        repository.findById(infoId)
                                .orElseThrow(() -> {
                                    throw new NotFoundException("Info not found");
                                }).getInterests())
        ).getData();
    }

    @Override
    public int count(String infoId) {
        validator.validKey(infoId);
        return repository.findById(infoId)
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                }).getInterests()
                .size();
    }

    @Override
    public void changeVisibility(AuthInfoChangeVisibilityDTO dto) {
        validator.validChangeVisibility(dto);
        AuthInfoEntity authInfo = repository.findByUserIdEquals(
                        authUserService.getEntity(dto.getId()))
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                });
        authInfo.setVisibility(dto.getVisibility());
        authInfo.setUpdatedAt(Instant.now());
        authInfo.setUpdatedBy(dto.getId());
        repository.save(authInfo);
        if (AuthInfoVisibilityEnum.PRIVATE.equals(dto.getVisibility()))
            inviteRepository.save(new AuthInviteEntity(authUserService.getEntity(dto.getId())));
    }

    @Override
    public void deleteByUserId(String id) {
        validator.validKey(id);
        repository.deleteByUserId(authUserService.getEntity(id));
    }

    private AuthInfoGetDTO returnToGetDTO(AuthInfoEntity entity) {
        AuthInfoGetDTO authInfoGetDTO = mapper.fromGetDTO(entity);
        authInfoGetDTO.setAuthid(entity.getUserId().getId());
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
