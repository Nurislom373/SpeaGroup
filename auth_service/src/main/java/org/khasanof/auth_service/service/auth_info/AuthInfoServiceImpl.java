package org.khasanof.auth_service.service.auth_info;

import org.khasanof.auth_service.criteria.auth_info.AuthInfoBetweenCriteria;
import org.khasanof.auth_service.criteria.auth_info.AuthInfoCriteria;
import org.khasanof.auth_service.criteria.auth_info.AuthInfoSearchCriteria;
import org.khasanof.auth_service.dto.auth_info.AuthInfoCreateDTO;
import org.khasanof.auth_service.dto.auth_info.AuthInfoDetailDTO;
import org.khasanof.auth_service.dto.auth_info.AuthInfoGetDTO;
import org.khasanof.auth_service.dto.auth_info.AuthInfoUpdateDTO;
import org.khasanof.auth_service.dto.location.LocationCreateDTO;
import org.khasanof.auth_service.dto.location.LocationUpdateDTO;
import org.khasanof.auth_service.entity.auth_info.AuthInfoEntity;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.entity.category.CategoryEntity;
import org.khasanof.auth_service.entity.location.LocationEntity;
import org.khasanof.auth_service.mapper.auth_info.AuthInfoMapper;
import org.khasanof.auth_service.repository.auth_info.AuthInfoRepository;
import org.khasanof.auth_service.repository.auth_user.AuthUserRepository;
import org.khasanof.auth_service.repository.category.CategoryRepository;
import org.khasanof.auth_service.service.AbstractService;
import org.khasanof.auth_service.validator.auth_info.AuthInfoValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

@Service
public class AuthInfoServiceImpl extends AbstractService<AuthInfoRepository, AuthInfoMapper, AuthInfoValidator> implements AuthInfoService {

    private final AuthUserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public AuthInfoServiceImpl(AuthInfoRepository repository, AuthInfoMapper mapper, AuthInfoValidator validator, AuthUserRepository userRepository, CategoryRepository categoryRepository) {
        super(repository, mapper, validator);
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void create(AuthInfoCreateDTO dto) {
        validator.validCreateDTO(dto);
        AuthUserEntity userEntity = userRepository.findById(dto.getAuthid()).orElseThrow(() -> {
            throw new NotFoundException("User not found");
        });
        List<CategoryEntity> list = StreamSupport.stream(
                categoryRepository.findAllById(
                        dto.getInterestsId()).spliterator(), false
        ).toList();
        if (list.isEmpty()) throw new NotFoundException("Category not found");
        AuthInfoEntity authInfoEntity = mapper.toCreateDTO(dto);
        authInfoEntity.setUserId(userEntity);
        authInfoEntity.setBornYear(strParseToDate(dto.getBornYearStr()));
        authInfoEntity.setInterests(list);
        repository.save(authInfoEntity);
    }

    @Override
    public void update(AuthInfoUpdateDTO dto) {
        validator.validUpdateDTO(dto);
        AuthInfoEntity entity = repository.findById(dto.getId()).orElseThrow(() -> {
            throw new NotFoundException("Info not found");
        });
        if (Objects.nonNull(dto.getInterestsId())) {
            List<CategoryEntity> categoryEntities = new ArrayList<>();
            dto.getInterestsId().forEach((obj) -> {
                categoryEntities.add(
                        categoryRepository.findById(obj).orElseThrow(() -> new NotFoundException("Category not found"))
                );
            });
            entity.setInterests(categoryEntities);
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
        AuthInfoEntity entity = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Info not found");
        });
        AuthInfoGetDTO getDTO = mapper.fromGetDTO(entity);
        entity.getInterests().forEach((obj) -> {
            getDTO.getInterestsId().add(obj.getId());
        });
        return getDTO;
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
        List<AuthInfoEntity> all = repository.findAll(
                PageRequest.of(
                        criteria.getPage(),
                        criteria.getSize(),
                        criteria.getDirection(),
                        criteria.getFieldsEnum().getValue()
                )).stream().toList();
        List<AuthInfoGetDTO> list = mapper.fromGetListDTO(all);
        for (int i = 0; i < all.size(); i++) {
            List<String> strings = repository.findAllById(all.get(i).getId());
            System.out.println("strings = " + strings);
            list.get(i).setInterestsId(strings);
        }
        return list;
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<AuthInfoGetDTO> listWithSc(AuthInfoSearchCriteria searchCriteria) {
        return null;
    }

    @Override
    public List<AuthInfoGetDTO> listWithBc(AuthInfoBetweenCriteria betweenCriteria) {
        return null;
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

    private Date strParseToDate(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}