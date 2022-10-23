package org.khasanof.auth_service.service.employment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.khasanof.auth_service.dto.employment.EmploymentCreateDTO;
import org.khasanof.auth_service.dto.employment.EmploymentGetDTO;
import org.khasanof.auth_service.enums.employment.EmploymentTypeEnum;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class EmploymentServiceTest {

    @Mock
    private EmploymentServiceImpl employmentService;

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
}
