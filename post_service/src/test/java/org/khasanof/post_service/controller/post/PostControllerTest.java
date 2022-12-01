package org.khasanof.post_service.controller.post;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.post_service.autoMock.AutoMockMvc;
import org.khasanof.post_service.autoMock.Utils;
import org.khasanof.post_service.dto.post.PostCreateDTO;
import org.khasanof.post_service.dto.post.PostUpdateDTO;
import org.khasanof.post_service.enums.post.PostVisibilityEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class PostControllerTest {

    @Autowired
    private AutoMockMvc mockMvc;

    @Test
    public void createMethodIsOkResponseTest() throws Exception {
        var dto = new PostCreateDTO("635920993c40830fe14b0cc0", "fidshuifgsufsgfgfd",
                "fdishfuidshfudgsyufdgsufgdsfdsfds", List.of("fduisgfudys.png", "fdishfuidhsuf.png"),
                PostVisibilityEnum.PUBLIC, List.of("gfdbgjd", "dusgufgdf"));

        mockMvc.obsessivePost(Utils.PATH + "/post/create", dto,
                Utils.matchers(Utils.StatusChoice.CREATED), MockMvcResultHandlers.print());
    }

    @Test
    public void createMethodIsBadRequestExceptionTest() {
        var dtos = List.of(
                new PostCreateDTO("635920993c40830fe14b0cc", "fidshuifgsufsgfgfd",
                        "fdishfuidshfudgsyufdgsufgdsfdsfds", List.of("fduisgfudys.png", "fdishfuidhsuf.png"),
                        PostVisibilityEnum.PUBLIC, List.of("gfdbgjd", "dusgufgdf")),

                new PostCreateDTO("635920993c40830fe14b0cc0", "fids",
                        "fdishfuidshfudgsyufdgsufgdsfdsfds", List.of("fduisgfudys.png", "fdishfuidhsuf.png"),
                        PostVisibilityEnum.PUBLIC, List.of("gfdbgjd", "dusgufgdf")),

                new PostCreateDTO("635920993c40830fe14b0cc0", "fidshuifgsufsgfgfd",
                        "fdishfuidshfudgsy", List.of("fduisgfudys.png", "fdishfuidhsuf.png"),
                        PostVisibilityEnum.PUBLIC, List.of("gfdbgjd", "dusgufgdf")),

                new PostCreateDTO("635920993c40830fe14b0cc0", "fidshuifgsufsgfgfd",
                        "fdishfuidshfudgsyufdgsufgdsfdsfdsgitkdhighfughfugfufgfhgfhgfhgfhgfhgf",
                        List.of("fduisgfudys.png", "fdishfuidhsuf.png"),
                        PostVisibilityEnum.PUBLIC, List.of("gfdbgjd", "dusgufgdf")),

                new PostCreateDTO("635920993c40830fe14b0cc0", "fidshuifgsufsgfgfd",
                        "fdishfuidshfudgsyufdgsufgdsfdsfdsgitkdhighfughfugfufgfhgfhgfhgfhgfhgf",
                        List.of("fduisgfudys.png", "fdishfuidhsuf.png"),
                        PostVisibilityEnum.PUBLIC, List.of("gfdbgjd", "dusgufgdf"))
        );
        dtos.forEach(dto -> {
            try {
                mockMvc.obsessivePost(Utils.PATH + "/post/create", dto,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void updateMethodIsOkResponseTest() throws Exception {
        var dto = new PostUpdateDTO("63878db5ae230f1379f039bb", "fdsufgdsufgdsfdsf",
                "fdysufg ds fdusf dsyfdsgufygdsyfgds fyds fydgsffdsfdsfd",
                PostVisibilityEnum.PRIVATE);

        mockMvc.obsessivePut(Utils.PATH + "/post/update", dto,
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void updateMethodIsBadRequestExceptionTest() {
        List.of(
                new PostUpdateDTO("63878db5ae230f1379f039b", "fdsufgdsufgdsfdsf",
                        "fdysufg ds fdusf dsyfdsgufygdsyfgds fyds fydgsffdsfdsfd",
                        PostVisibilityEnum.PRIVATE),
                new PostUpdateDTO("63878db5ae230f1379f039bb", "fdsu",
                        "fdysufg ds fdusf dsyfdsgufygdsyfgds fyds fydgsffdsfdsfd",
                        PostVisibilityEnum.PRIVATE),
                new PostUpdateDTO("63878db5ae230f1379f039bb", "fdsufgdsufgdsfdsf",
                        "fdysufg ds fdusf",
                        PostVisibilityEnum.PRIVATE)
        ).forEach(var -> {
            try {
                mockMvc.obsessivePut(Utils.PATH + "/post/update", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void updateMethodIsNotFoundExceptionTest() {
        List.of(
                new PostUpdateDTO("63878db5ae230f1379f039b1", "fdsufgdsufgdsfdsf",
                        "fdysufg ds fdusf dsyfdsgufygdsyfgds fyds fydgsffdsfdsfd",
                        PostVisibilityEnum.PRIVATE),
                new PostUpdateDTO("63878db5ae230f1379f039b2", "fdsufdsfdsfdsfdsfds",
                        "fdysufg ds fdusf dsyfdsgufygdsyfgds fyds fydgsffdsfdsfd",
                        PostVisibilityEnum.PRIVATE),
                new PostUpdateDTO("63878db5ae230f1379f039b3", "fdsufgdsufgdsfdsf",
                        "fdysufg ds fdusf dsyfdsgufygdsyfgds fyds fydgsffdsfdsfd",
                        PostVisibilityEnum.PRIVATE)
        ).forEach(var -> {
            try {
                mockMvc.obsessivePut(Utils.PATH + "/post/update", var,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void getMethodIsOkResponseTest() throws Exception {
        var var = "6387989065fb6c66bd567248";
        mockMvc.obsessiveGet(Utils.PATH + "/post/get/{id}", var,
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void getMethodIsBadRequestExceptionTest() {
        List.of("fdusgfvs", "84n367", "872153", " ")
                .forEach(var -> {
                    try {
                        mockMvc.obsessiveGet(Utils.PATH + "/post/get/{id}", var,
                                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void getMethodIsNotFoundExceptionTest() {
        List.of("6387989065fb6c66bd567249", "6387989065fb6c66bd567247",
                        "6387989065fb6c66bd567246", "6387989065fb6c66bd567245")
                .forEach(var -> {
                    try {
                        mockMvc.obsessiveGet(Utils.PATH + "/post/get/{id}", var,
                                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void detailMethodIsOkResponseTest() throws Exception {
        var var = "6387989065fb6c66bd567248";
        mockMvc.obsessiveGet(Utils.PATH + "/post/detail/{id}", var,
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void detailMethodIsBadRequestExceptionTest() {
        List.of("fdusgfvs", "84n367", "872153", " ")
                .forEach(var -> {
                    try {
                        mockMvc.obsessiveGet(Utils.PATH + "/post/detail/{id}", var,
                                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void detailMethodIsNotFoundExceptionTest() {
        List.of("6387989065fb6c66bd567249", "6387989065fb6c66bd567247",
                        "6387989065fb6c66bd567246", "6387989065fb6c66bd567245")
                .forEach(var -> {
                    try {
                        mockMvc.obsessiveGet(Utils.PATH + "/post/detail/{id}", var,
                                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void deleteMethodIsNoContentResponseTest() throws Exception {
        var var = "6387989065fb6c66bd567248";
        mockMvc.obsessiveDelete(Utils.PATH + "/post/delete/{id}", var,
                Utils.matchers(Utils.StatusChoice.NO_CONTENT), MockMvcResultHandlers.print());
    }

    @Test
    public void deleteMethodIsBadRequestExceptionTest() {
        List.of("fdusgfvs", "84n367", "872153", " ")
                .forEach(var -> {
                    try {
                        mockMvc.obsessiveDelete(Utils.PATH + "/post/delete/{id}", var,
                                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void deleteMethodIsNotFoundExceptionTest() {
        List.of("6387989065fb6c66bd567249", "6387989065fb6c66bd567247",
                        "6387989065fb6c66bd567246", "6387989065fb6c66bd567245")
                .forEach(var -> {
                    try {
                        mockMvc.obsessiveDelete(Utils.PATH + "/post/delete/{id}", var,
                                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void listMethodIsOkResponseTest() throws Exception {
        LinkedMultiValueMap<String, String> map = Utils.MAP;
        map.add("fieldsEnum", "TITLE");
        mockMvc.obsessiveGet(Utils.PATH + "/post/list", map,
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

}
