package org.khasanof.auth_service.service.employment;

import org.khasanof.auth_service.dto.employment.EmploymentCreateDTO;
import org.khasanof.auth_service.dto.employment.EmploymentGetDTO;
import org.khasanof.auth_service.dto.employment.EmploymentUpdateDTO;
import org.khasanof.auth_service.service.BaseService;

import java.util.List;

public interface EmploymentService extends BaseService {
    void add(EmploymentCreateDTO dto);

    void addAll(List<EmploymentCreateDTO> dtos);

    void update(EmploymentUpdateDTO dto);

    void updateAll(List<EmploymentUpdateDTO> dtos);

    void delete(String infoId, String id);

    EmploymentGetDTO getEmployment(String infoId, String id);

    List<EmploymentGetDTO> listEmployments(String infoId);

    long countEmployment(String infoId);
}
