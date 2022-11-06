package org.khasanof.auth_service.controller.education;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.auth_service.autoMock.AutoMockMvc;
import org.khasanof.auth_service.autoMock.Utils;
import org.khasanof.auth_service.dto.education.EducationCreateDTO;
import org.khasanof.auth_service.dto.education.EducationUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class EducationControllerTest {

    @Autowired
    private AutoMockMvc autoMockMvc;

    @Test
    public void addMethodIsOkTest() throws Exception {
        var body = new EducationCreateDTO("6361625ffb716e1c2b2dc202", "PDP IT Academy", "18/10/2019", "18/10/2022", "fdsfvs", "fdusfdyusgfyudsg");
        autoMockMvc.obsessivePost("/api/v1/education/add", body,
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void addMethodIsBadRequestTest() throws Exception {
        var list = List.of(
                new EducationCreateDTO("6361625ffb716e1c2b2dc20", "PDP IT Academy", "18/10/2019", "18/10/2022", "fdsfvs", "fdusfdyusgfyudsg"),
                new EducationCreateDTO("6361625ffb716e1c2b2dc202", "P", "18/10/2019", "18/10/2022", "fdsfvs", "fdusfdyusgfyudsg"),
                new EducationCreateDTO("6361625ffb716e1c2b2dc202", "PDP IT Academy", "18/40/2019", "18/10/2022", "fdsfvs", "fdusfdyusgfyudsg"),
                new EducationCreateDTO("6361625ffb716e1c2b2dc202", "PDP IT Academy", "18/10/2019", "38/10/2022", "fds", "fdusfdyusgfyudsg")
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePost("/api/v1/education/add", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void addMethodIsNotFoundTest() throws Exception {
        var body = new EducationCreateDTO("6361625ffb716e1c2b2dc201", "PDP IT Academy", "18/10/2019", "18/10/2022", "fdsfvs", "fdusfdyusgfyudsg");
        autoMockMvc.obsessivePost("/api/v1/education/add", body,
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
    }

    @Test
    public void updateMethodIsOkTest() throws Exception {
        var list = List.of(
                new EducationUpdateDTO("6361625ffb716e1c2b2dc202", "1667742660860", "PDPIT",
                        "18/10/2019", "18/10/2022", "fdsfvs", "fdusfdyusgfyudsg"));
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/education/update", var,
                        Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void updateMethodIsBadRequestTest() throws Exception {
        var list = List.of(

                new EducationUpdateDTO("6361625ffb716e1c2b2dc20", "1667742660860", "PDPIT",
                        "18/10/2019", "18/10/2022", "fdsfvs", "fdusfdyusgfyudsg"),

                new EducationUpdateDTO("1", "166774266086", "PDPIT",
                        "18/10/2019", "18/10/2022", "fdsfvs", "fdusfdyusgfyudsg"),

                new EducationUpdateDTO("6361625ffb716e1c2b2dc202", "1667742660860", "PDPIT",
                        "18/40/2019", "18/10/2022", "fdsfvs", "fdusfdyusgfyudsg"),

                new EducationUpdateDTO("63628e0f6eebe000c3dc798b", "1667742660860", "PDPIT",
                        "18/10/2019", "18/10/2022", "fdsfvs", "fdusfdyusgfyudsg"),

                new EducationUpdateDTO("6361625ffb716e1c2b2dc202", "1667742660860", "PDPIT",
                        "18/10/2019", "18/10/2022", "fd", "fdusfdyusgfyudsg"),

                new EducationUpdateDTO("6361625ffb716e1c2b2dc202", "1667742660860", "PDPIT",
                        "18/10/2019", "18/10/2022", "fdfdsfds", "fd"),

                new EducationUpdateDTO("6361625ffb716e1c2b2dc202", "1667742660860", "PDPIT",
                        "18/10/2019", "48/10/2022", "fdsfvs", "fdusfdyusgfyudsg")
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/education/update", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void updateMethodIsNotFoundTest() throws Exception {
        var list = List.of(

                new EducationUpdateDTO("6361625ffb716e1c2b2dc201", "1667742660860", "PDPIT",
                        "18/10/2019", "18/10/2022", "fdsfvs", "fdusfdyusgfyudsg"),

                new EducationUpdateDTO("6361625ffb716e1c2b2dc202", "1667742660861", "PDPIT",
                        "18/10/2019", "18/10/2022", "fdsfvs", "fdusfdyusgfyudsg"),

                new EducationUpdateDTO("6361625ffb716e1c2b2dc202", "r389", "PDPIT",
                        "18/10/2019", "18/10/2022", "fdsfvs", "fdusfdyusgfyudsg")
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/education/update", var,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void deleteMethodIsNoContentTest() throws Exception {
        autoMockMvc.obsessiveDelete("/api/v1/education/delete/infoId=6361625ffb716e1c2b2dc202&id=1667745784133",
                Utils.matchers(Utils.StatusChoice.NO_CONTENT), MockMvcResultHandlers.print());
    }

    @Test
    public void deleteMethodIsBadRequestTest() throws Exception {
        var list = new HashMap<String, String>();
        list.put("6361625ffb716e1c2b2dc20", "1667745784133");
        list.put("483u", "1667745784133");
        list.put("9833", "1667745784133");
        list.put("$3829hei2", "1667745784133");
        list.put("", "1667745784133");
        list.forEach((var1, var2) -> {
            try {
                autoMockMvc.obsessiveDelete("/api/v1/education/delete/infoId=" + var1 + "&id=" + var2,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void deleteMethodIsNotFoundTest() throws Exception {
        autoMockMvc.obsessiveDelete("/api/v1/education/delete/infoId=6361625ffb716e1c2b2dc202&id=1667745784133",
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
    }

    @Test
    public void getMethodIsOkTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/education/get/infoId=6361625ffb716e1c2b2dc202&id=1667742660860",
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void getMethodIsBadRequestTest() {
        var map = new HashMap<String, String>();
        map.put("6361625ffb716e1c2b2dc20", "u73gudy3gdf");
        map.put("1y67fr32yutd2", "u73gudy3gdf");
        map.put("3guygouy2f1", "u73gudy3gdf");
        map.put("", "u73gudy3gdf");
        map.forEach((elem1, elem2) -> {
            try {
                autoMockMvc.obsessiveGet("/api/v1/education/get/infoId=" + elem1 + "&id=" + elem2,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void getMethodIsNotFoundTest() throws Exception {
        var map = new HashMap<String, String>();
        map.put("6361625ffb716e1c2b2dc212", "grkgnjfk");
        map.put("6361625ffb716e1c2b2dc201", "ifrh");
        map.put("6361625ffb716e1c2b2dc202", "frui");
        map.put("6361625ffb716e1c2b2dc203", "ufig");
        map.put("6361625ffb716e1c2b2dc204", "ufdb");
        map.put("6361625ffb716e1c2b2dc205", "hfuid");
        map.put("6361625ffb716e1c2b2dc206", "fudig");
        map.forEach((elem1, elem2) -> {
            try {
                autoMockMvc.obsessiveGet("/api/v1/education/get/infoId=" + elem1 + "&id=" + elem2,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void getAllMethodIsOkTest() throws Exception {
        var list = List.of("6361625ffb716e1c2b2dc202");
        list.forEach((elem) -> {
            try {
                autoMockMvc.obsessiveGet("/api/v1/education/getAll/" + elem,
                        Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void getAllMethodIsBadRequestTest() throws Exception {
        var list = List.of("6361625ffb716e1c2b2dc20", "fiuesbuif", "fdjskbfs", "fsjbfds");
        list.forEach((elem) -> {
            try {
                autoMockMvc.obsessiveGet("/api/v1/education/getAll/" + elem,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void getAllMethodIsNotFoundTest() throws Exception {
        var list = List.of("6361625ffb716e1c2b2dc201", "6361625ffb716e1c2b2dc203");
        list.forEach((elem) -> {
            try {
                autoMockMvc.obsessiveGet("/api/v1/education/getAll/" + elem,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }




}
