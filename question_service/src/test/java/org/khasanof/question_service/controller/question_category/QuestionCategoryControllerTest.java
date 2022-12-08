package org.khasanof.question_service.controller.question_category;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.question_service.autoMock.AutoMockMvc;
import org.khasanof.question_service.autoMock.Utils;
import org.khasanof.question_service.dto.question_category.QuestionCategoryCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/8/2022
 * <br/>
 * Time: 10:31 PM
 * <br/>
 * Package: org.khasanof.question_service.controller.question_category
 */
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class QuestionCategoryControllerTest {

    @Autowired
    private AutoMockMvc autoMockMvc;

    @Test
    public void createMethodIsBadRequestResponseTest() throws Exception {
        var content = new QuestionCategoryCreateDTO("fugyhgsgdggfd", List.of("gdsgfgfd", "fdsfds"));
        autoMockMvc.obsessivePost("/api/v1/question_category/create", content,
                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
    }

}
