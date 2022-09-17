package org.khasanof.auth_service.service.education;

import org.khasanof.auth_service.dto.education.EducationCreateDTO;
import org.khasanof.auth_service.dto.education.EducationGetDTO;
import org.khasanof.auth_service.dto.education.EducationUpdateDTO;
import org.khasanof.auth_service.service.BaseService;

import java.util.List;

public interface EducationService extends BaseService {

    void add(EducationCreateDTO dto);

    void addAll(List<EducationCreateDTO> dtos);

    void update(EducationUpdateDTO dto);

    void updateAll(List<EducationUpdateDTO> dtos);

    void delete(String infoId, String id);

    EducationGetDTO getEducation(String infoId, String id);

    List<EducationGetDTO> listEducations(String infoId);

    long countEducation(String infoId);

}
