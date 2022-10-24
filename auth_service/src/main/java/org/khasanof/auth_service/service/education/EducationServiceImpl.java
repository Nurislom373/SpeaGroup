package org.khasanof.auth_service.service.education;

import org.khasanof.auth_service.dto.education.EducationCreateDTO;
import org.khasanof.auth_service.dto.education.EducationGetDTO;
import org.khasanof.auth_service.dto.education.EducationUpdateDTO;
import org.khasanof.auth_service.entity.auth_info.AuthInfoEntity;
import org.khasanof.auth_service.entity.education.EducationEntity;
import org.khasanof.auth_service.mapper.education.EducationMapper;
import org.khasanof.auth_service.repository.auth_info.AuthInfoRepository;
import org.khasanof.auth_service.service.AbstractService;
import org.khasanof.auth_service.validator.education.EducationValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EducationServiceImpl extends AbstractService<AuthInfoRepository, EducationMapper, EducationValidator> implements EducationService {

    public EducationServiceImpl(AuthInfoRepository repository, EducationMapper mapper, EducationValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public void add(EducationCreateDTO dto) {
        validator.validCreateDTO(dto);
        AuthInfoEntity entity = repository.findById(dto.getInfoId())
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                });
        List<EducationEntity> list;
        if (entity.getEducations().size() < 1) {
            list = new ArrayList<>();
            list.add(new EducationEntity(dto.getEducation(), strParseToDate(dto.getStartYearStr()),
                    strParseToDate(dto.getEndYearStr()), dto.getPrimaryMajor(), dto.getSecondaryMajor()));
        } else {
            list = entity.getEducations();
            EducationEntity educationEntity = mapper.toCreateDTO(dto);
            educationEntity.setId(String.valueOf(System.currentTimeMillis()));
            educationEntity.setStartYear(strParseToDate(dto.getStartYearStr()));
            educationEntity.setEndYear(strParseToDate(dto.getEndYearStr()));
            list.add(educationEntity);
        }
        entity.setEducations(list);
        entity.setUpdatedAt(Instant.now());
        entity.setUpdatedBy(entity.getUserId().getId());
        repository.save(entity);
    }

    @Override
    public void addAll(List<EducationCreateDTO> dtos) {
        dtos.forEach((this::add));
    }

    @Override
    public void update(EducationUpdateDTO dto) {
        validator.validUpdateDTO(dto);
        AuthInfoEntity entity = repository.findById(dto.getInfoId())
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                });
        List<EducationEntity> educations = entity.getEducations();
        if (educations.isEmpty()) {
            throw new RuntimeException("Education is null!");
        }
        EducationEntity education = educations.stream()
                .filter(f -> f.getId().equals(dto.getEducationId()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Education not found"));
        educations.remove(education);
        educations.add(swapToEntity(education, dto));
        entity.setEducations(educations);
        entity.setUpdatedAt(Instant.now());
        entity.setUpdatedBy(entity.getUserId().getId());
        repository.save(entity);
    }

    @Override
    public void updateAll(List<EducationUpdateDTO> dtos) {
        dtos.forEach((this::update));
    }

    @Override
    public void delete(String infoId, String id) {
        validator.validKey(infoId);
        validator.validKey(id);
        AuthInfoEntity entity = repository.findById(infoId)
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                });
        List<EducationEntity> educations = entity.getEducations();
        if (educations.isEmpty()) {
            throw new RuntimeException("Education is null!");
        }
        if (!educations.removeIf(f -> f.getId().equals(id))) {
            throw new NotFoundException("Education not found");
        }
        entity.setEducations(educations);
        entity.setUpdatedAt(Instant.now());
        entity.setUpdatedBy(entity.getUserId().getId());
        repository.save(entity);
    }

    @Override
    public EducationGetDTO getEducation(String infoId, String id) {
        validator.validKey(infoId);
        validator.validKey(id);
        AuthInfoEntity entity = repository.findById(infoId)
                .orElseThrow(() -> new NotFoundException("Info not found"));
        return mapper.fromGetDTO(entity.getEducations()
                .stream()
                .filter(obj -> id.equals(obj.getId()))
                .findAny()
                .orElseThrow(() -> new NotFoundException("Education not found")));
    }

    @Override
    public List<EducationGetDTO> listEducations(String infoId) {
        return mapper.fromGetListDTO(
                repository.findById(infoId)
                        .orElseThrow(() -> new NotFoundException("Info not found"))
                        .getEducations()
        );
    }

    @Override
    public long countEducation(String infoId) {
        return repository.findById(infoId)
                .orElseThrow(() -> new NotFoundException("Info not found"))
                .getEducations()
                .size();
    }

    private EducationEntity swapToEntity(EducationEntity entity, EducationUpdateDTO dto) {
        BeanUtils.copyProperties(dto, entity);
        entity.setId(dto.getEducationId());
        entity.setEndYear(strParseToDate(dto.getEndYearStr()));
        entity.setStartYear(strParseToDate(dto.getStartYearStr()));
        return entity;
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
