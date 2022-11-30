package org.khasanof.post_service.controller.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.post_service.autoMock.AutoMockMvc;
import org.khasanof.post_service.autoMock.Utils;
import org.khasanof.post_service.dto.post.PostCreateDTO;
import org.khasanof.post_service.enums.post.PostVisibilityEnum;
import org.khasanof.post_service.service.post.PostServiceImpl;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class PostControllerTest {

    @Autowired
    private AutoMockMvc mockMvc;

    @MockBean
    private PostServiceImpl service;


    @Test
    public void createMethodIsOkTest() throws Exception {
        var dto = new PostCreateDTO("635920993c40830fe14b0cc0", "fidshuifgsufsgfgfd",
                "fdishfuidshfudgsyufdgsufgdsfdsfds", List.of("fduisgfudys.png", "fdishfuidhsuf.png"),
                PostVisibilityEnum.PUBLIC, List.of("gfdbgjd", "dusgufgdf"));

        mockMvc.obsessivePost(Utils.PATH + "/post/create", dto,
                Utils.matchers(Utils.StatusChoice.CREATED), MockMvcResultHandlers.print());
    }

}
