package org.khasanof.auth_service.service.employment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.auth_service.dto.employment.EmploymentCreateDTO;
import org.khasanof.auth_service.dto.employment.EmploymentGetDTO;
import org.khasanof.auth_service.enums.employment.EmploymentTypeEnum;
import org.khasanof.auth_service.repository.auth_info.AuthInfoRepository;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmploymentServiceTest {

    @Autowired
    private EmploymentService employmentService;

    @MockBean
    private AuthInfoRepository repository;

    @Test
    public void createMethodTest() throws ParseException {
        EmploymentCreateDTO dto = new EmploymentCreateDTO(
                "63343ac6f2da927b960fb2bd", "PDP", "Software Engineer",
                "18/10/202", "20/12/2021", EmploymentTypeEnum.FULL_TIME);
        employmentService.add(dto);
        Mockito.verify(employmentService).add(dto);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        List<EmploymentGetDTO> dtos = List.of(new EmploymentGetDTO("63343ac6f2da927b960fb2bd", "PDP",
                "Software Engineer", format.parse("18/10/2021"),
                format.parse("20/12/2021"), EmploymentTypeEnum.FULL_TIME));
        Mockito.when(employmentService.listEmployments(ArgumentMatchers.any())).thenReturn(dtos);
        List<EmploymentGetDTO> list = employmentService.listEmployments("63343ac6f2da927b960fb2bd");
        Assertions.assertEquals(1, list.size());
    }

    @Test
    public void listEmploymentMethodTest() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        List<EmploymentGetDTO> list = List.of(
                new EmploymentGetDTO("635920993c40830fe14b0cc0", "PDP", "Software Engineer",
                        format.parse("18/10/2021"),
                        format.parse("18/10/2021"), EmploymentTypeEnum.FULL_TIME),
                new EmploymentGetDTO("635920993c40830fe14b0cc1", "Google", "Software Engineer",
                        format.parse("18/10/2021"),
                        format.parse("18/10/2021"), EmploymentTypeEnum.FULL_TIME)
        );

    }
}
