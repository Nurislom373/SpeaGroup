package org.khasanof.auth_service.service.employment;

import org.khasanof.auth_service.dto.employment.EmploymentCreateDTO;
import org.khasanof.auth_service.dto.employment.EmploymentGetDTO;
import org.khasanof.auth_service.dto.employment.EmploymentUpdateDTO;
import org.khasanof.auth_service.entity.auth_info.AuthInfoEntity;
import org.khasanof.auth_service.entity.employment.EmploymentEntity;
import org.khasanof.auth_service.exception.exceptions.ListIsNullException;
import org.khasanof.auth_service.mapper.employment.EmploymentMapper;
import org.khasanof.auth_service.repository.auth_info.AuthInfoRepository;
import org.khasanof.auth_service.service.AbstractService;
import org.khasanof.auth_service.validator.employment.EmploymentValidator;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class EmploymentServiceImpl extends AbstractService<AuthInfoRepository, EmploymentMapper, EmploymentValidator> implements EmploymentService {

    public EmploymentServiceImpl(AuthInfoRepository repository, EmploymentMapper mapper, EmploymentValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public void add(EmploymentCreateDTO dto) {
        validator.validCreateDTO(dto);
        AuthInfoEntity entity = repository.findById(dto.getInfoId())
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                });
        List<EmploymentEntity> list;
        if (Objects.isNull(entity.getEmployments())) {
            list = new ArrayList<>();
            list.add(new EmploymentEntity(dto.getCompany(), dto.getPosition(),
                    strParseToDate(dto.getStartYearStr()), strParseToDate(dto.getEndYearStr()),
                    dto.getType()));
        } else {
            list = entity.getEmployments();
            EmploymentEntity employmentEntity = mapper.toCreateDTO(dto);
            employmentEntity.setId(String.valueOf(System.currentTimeMillis()));
            employmentEntity.setStartYear(strParseToDate(dto.getStartYearStr()));
            employmentEntity.setEndYear(strParseToDate(dto.getEndYearStr()));
            list.add(employmentEntity);
        }
        entity.setEmployments(list);
        entity.setUpdatedAt(Instant.now());
        entity.setUpdatedBy(entity.getUserId().getId());
        repository.save(entity);
    }

    @Override
    public void addAll(List<EmploymentCreateDTO> dtos) {
        dtos.forEach(this::add);
    }

    @Override
    public void update(EmploymentUpdateDTO dto) {
        validator.validUpdateDTO(dto);
        AuthInfoEntity entity = repository.findById(dto.getInfoId())
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                });
        List<EmploymentEntity> employments = entity.getEmployments();
        if (Objects.isNull(employments)) {
            throw new ListIsNullException("Employment is null!");
        }
        EmploymentEntity employment = employments.stream()
                .filter(f -> f.getId().equals(dto.getEmploymentId()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Employment not found"));
        employments.remove(employment);
        employments.add(swapObj(dto, employment));
        entity.setEmployments(employments);
        entity.setUpdatedAt(Instant.now());
        entity.setUpdatedBy(entity.getUserId().getId());
        repository.save(entity);
    }

    @Override
    public void updateAll(List<EmploymentUpdateDTO> dtos) {
        dtos.forEach(this::update);
    }

    @Override
    public void delete(String infoId, String id) {
        validator.validKey(infoId);
        AuthInfoEntity entity = repository.findById(infoId)
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                });
        List<EmploymentEntity> employments = entity.getEmployments();
        if (employments.isEmpty()) {
            throw new ListIsNullException("Employments is null");
        }
        if (!employments.removeIf(f -> f.getId().equals(id))) {
            throw new NotFoundException("Employment not found");
        }
        entity.setEmployments(employments);
        entity.setUpdatedAt(Instant.now());
        entity.setUpdatedBy(entity.getUserId().getId());
        repository.save(entity);
    }

    @Override
    public EmploymentGetDTO getEmployment(String infoId, String id) {
        validator.validKey(infoId);
        return mapper.fromGetDTO(
                repository.findById(infoId)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Info not found");
                        }).getEmployments().stream().filter(
                                (obj) -> obj.getId().equals(id)
                        ).findAny().orElseThrow(() -> {
                            throw new NotFoundException("Employment not found");
                        }));
    }

    @Override
    public List<EmploymentGetDTO> listEmployments(String infoId) {
        validator.validKey(infoId);
        return mapper.fromGetListDTO(
                repository.findById(infoId)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Info not found");
                        }).getEmployments());
    }

    @Override
    public long countEmployment(String infoId) {
        validator.validKey(infoId);
        return repository.findById(infoId)
                .orElseThrow(() -> {
                    throw new NotFoundException("Info not found");
                }).getEmployments()
                .size();
    }

    private EmploymentEntity swapObj(EmploymentUpdateDTO dto, EmploymentEntity entity) {
        entity.setType(dto.getType());
        entity.setCompany(entity.getCompany());
        entity.setPosition(dto.getPosition());
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
