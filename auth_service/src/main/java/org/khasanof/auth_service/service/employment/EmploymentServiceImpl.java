package org.khasanof.auth_service.service.employment;

import org.khasanof.auth_service.dto.employment.EmploymentCreateDTO;
import org.khasanof.auth_service.dto.employment.EmploymentGetDTO;
import org.khasanof.auth_service.dto.employment.EmploymentUpdateDTO;
import org.khasanof.auth_service.entity.auth_info.AuthInfoEntity;
import org.khasanof.auth_service.entity.employment.EmploymentEntity;
import org.khasanof.auth_service.mapper.employment.EmploymentMapper;
import org.khasanof.auth_service.repository.auth_info.AuthInfoRepository;
import org.khasanof.auth_service.service.AbstractService;
import org.khasanof.auth_service.validator.employment.EmploymentValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
        EmploymentEntity employmentEntity = mapper.toCreateDTO(dto);
        employmentEntity.setStartYear(strParseToDate(dto.getStartYearStr()));
        employmentEntity.setEndYear(strParseToDate(dto.getEndYearStr()));
        entity.getEmployments().add(employmentEntity);
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
        AtomicBoolean define = new AtomicBoolean(false);
        entity.getEmployments().forEach((emp) -> {
            if (dto.getId().equals(emp.getId())) {
                emp.setEndYear(strParseToDate(dto.getEndYearStr()));
                emp.setStartYear(strParseToDate(dto.getStartYearStr()));
                BeanUtils.copyProperties(dto, emp);
                define.set(true);
            }
        });
        if (!define.get()) throw new NotFoundException("Employment not found");
        repository.save(entity);

    }

    @Override
    public void updateAll(List<EmploymentUpdateDTO> dtos) {
        dtos.forEach(this::update);
    }

    @Override
    public void delete(String infoId, String id) {
        validator.validKey(infoId);
        validator.validKey(id);
        AuthInfoEntity entity = repository.findById(infoId).orElseThrow(() -> {
            throw new NotFoundException("Info not found");
        });
        AtomicBoolean define = new AtomicBoolean(false);
        entity.getEmployments().forEach((emp) -> {
            if (id.equals(emp.getId())) {
                entity.getEmployments().remove(emp);
                define.set(true);
            }
        });
        if (!define.get()) throw new NotFoundException("Employment not found");
        repository.save(entity);
    }

    @Override
    public EmploymentGetDTO getEmployment(String infoId, String id) {
        return null;
    }

    @Override
    public List<EmploymentGetDTO> listEmployments(String infoId) {
        return null;
    }

    @Override
    public long countEmployment(String infoId) {
        return 0;
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
