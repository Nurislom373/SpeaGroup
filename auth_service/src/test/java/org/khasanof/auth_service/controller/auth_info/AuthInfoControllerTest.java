package org.khasanof.auth_service.controller.auth_info;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khasanof.auth_service.autoMock.AutoMockMvc;
import org.khasanof.auth_service.autoMock.Utils;
import org.khasanof.auth_service.dto.auth_info.AuthInfoChangeVisibilityDTO;
import org.khasanof.auth_service.dto.auth_info.AuthInfoCreateDTO;
import org.khasanof.auth_service.dto.auth_info.AuthInfoUpdateDTO;
import org.khasanof.auth_service.dto.category.CategoryAddAllDTO;
import org.khasanof.auth_service.dto.category.CategoryAddDTO;
import org.khasanof.auth_service.dto.category.CategoryDeleteAllDTO;
import org.khasanof.auth_service.dto.category.CategoryDeleteDTO;
import org.khasanof.auth_service.dto.location.LocationCreateDTO;
import org.khasanof.auth_service.dto.location.LocationUpdateDTO;
import org.khasanof.auth_service.enums.auth_info.AuthInfoVisibilityEnum;
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
public class AuthInfoControllerTest {

    @Autowired
    private AutoMockMvc autoMockMvc;

    @Test
    public void createMethodIsCreatedTest() throws Exception {
        var list = List.of(
                new AuthInfoCreateDTO("6320c3dbef1ded597035d89a", List.of("6325ee332088c03f152df33c", "6325ee332088c03f152df33d")),
                new AuthInfoCreateDTO("6320c3dbef1ded597035d89b", List.of("6325ee332088c03f152df33e", "6325ee332088c03f152df33f")),
                new AuthInfoCreateDTO("6320c3dbef1ded597035d89c", List.of("6325ee332088c03f152df340", "6325ee332088c03f152df341")));

        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePost("/api/v1/auth_info/create", var,
                        Utils.matchers(Utils.StatusChoice.CREATED), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void createMethodIsBadRequestTest() throws Exception {
        var list = List.of(
                new AuthInfoCreateDTO("6320c3dbef1ded597035d89", List.of("6325ee332088c03f152df33", "6325ee332088c03f152df33d")),
                new AuthInfoCreateDTO("2415321", List.of("6325ee332088c03f152df33e", "6325ee332088c03f152df33f")),
                new AuthInfoCreateDTO("$gyufdg", List.of("6325ee332088c03f152df340", "6325ee332088c03f152df341")));

        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePost("/api/v1/auth_info/create", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void createMethodIsNotAcceptableTest() throws Exception {
        var list = List.of(
                new AuthInfoCreateDTO("6320c3dbef1ded597035d89a", List.of("6325ee332088c03f152df33", "6325ee332088c03f152df33d")));
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePost("/api/v1/auth_info/create", var,
                        Utils.matchers(Utils.StatusChoice.NOT_ACCEPTABLE), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void createMethodIsNotFoundTest() throws Exception {
        var list = List.of(
                new AuthInfoCreateDTO("6320c3dbef1ded597035d891", List.of("6325ee332088c03f152df33", "6325ee332088c03f152df33d")));
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePost("/api/v1/auth_info/create", var,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void updateMethodIsOkTest() throws Exception {
        var list = List.of(
                new AuthInfoUpdateDTO("63628e0f6eebe000c3dc798b", "2006", "+9985764653"));
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/auth_info/update", var,
                        Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void updateMethodIsBadRequestTest() throws Exception {
        var list = List.of(
                new AuthInfoUpdateDTO("63628e0f6eebe000c3dc798", "2006", "+9985764653"),
                new AuthInfoUpdateDTO("563372", "2006", "+9985764653")
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/auth_info/update", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void updateMethodIsNotFoundTest() throws Exception {
        var list = List.of(
                new AuthInfoUpdateDTO("6361625ffb716e1c2b2dc201", "2006", "+9985764653"),
                new AuthInfoUpdateDTO("6361625ffb716e1c2b2dc203", "2006", "+9985764653")
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/auth_info/update", var,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void addLocationMethodIsOkTest() throws Exception {
        var list = List.of(
                new LocationCreateDTO("6361625ffb716e1c2b2dc202", "Uzbekistan", "Tashkent"),
                new LocationCreateDTO("63628e0f6eebe000c3dc798b", "USA", "Washington")
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/auth_info/addLocation", var,
                        Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void addLocationMethodIsBadRequestTest() throws Exception {
        var list = List.of(
                new LocationCreateDTO("6361625ffb716e1c2b2dc20", "Uzbekistan", "Tashkent"),
                new LocationCreateDTO("63628e0f6eebe000c3dc798", "US", "Washington")
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/auth_info/addLocation", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void addLocationMethodIsNotAcceptableTest() throws Exception {
        var list = List.of(
                new LocationCreateDTO("6361625ffb716e1c2b2dc202", "Uzbekistan", "Tashkent"),
                new LocationCreateDTO("63628e0f6eebe000c3dc798b", "US", "Washington")
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/auth_info/addLocation", var,
                        Utils.matchers(Utils.StatusChoice.NOT_ACCEPTABLE), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void addLocationMethodIsNotFoundTest() throws Exception {
        var obj = new LocationCreateDTO("6361625ffb716e1c2b2dc201", "Uzbekistan", "Tashkent");
        autoMockMvc.obsessivePut("/api/v1/auth_info/addLocation", obj,
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
    }

    @Test
    public void updateLocationMethodIsOkTest() throws Exception {
        var list = List.of(
                new LocationUpdateDTO("6361625ffb716e1c2b2dc202", "UzbekistanUPD", "TashkentUPD"),
                new LocationUpdateDTO("63628e0f6eebe000c3dc798b", "US", "WashingtonUPD")
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/auth_info/updateLocation", var,
                        Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void updateLocationMethodIsBadRequestTest() throws Exception {
        var list = List.of(
                new LocationUpdateDTO("6361625ffb716e1c2b2dc20", "UzbekistanUPD", "TashkentUPD"),
                new LocationUpdateDTO("63628e0f6eebe000c3dc798", "US", "WashingtonUPD")
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/auth_info/updateLocation", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void updateLocationMethodIsNotFoundTest() throws Exception {
        var list = List.of(
                new LocationUpdateDTO("6361625ffb716e1c2b2dc201", "UzbekistanUPD", "TashkentUPD"),
                new LocationUpdateDTO("63628e0f6eebe000c3dc7981", "US", "WashingtonUPD")
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/auth_info/updateLocation", var,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }


    @Test
    public void deleteLocationMethodIsOkTest() throws Exception {
        autoMockMvc.obsessiveDelete("/api/v1/auth_info/deleteLocation/{id}", "63628e0f6eebe000c3dc798d",
                Utils.matchers(Utils.StatusChoice.NO_CONTENT), MockMvcResultHandlers.print());
    }

    @Test
    public void deleteLocationMethodIsBadRequestTest() throws Exception {
        autoMockMvc.obsessiveDelete("/api/v1/auth_info/deleteLocation/{id}", "63628e0f6eebe000c3dc798",
                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
    }

    @Test
    public void deleteLocationMethodIsNotFoundTest() throws Exception {
        autoMockMvc.obsessiveDelete("/api/v1/auth_info/deleteLocation/{id}", "63628e0f6eebe000c3dc7981",
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
    }

    @Test
    public void addCategoryMethodIsOkTest() throws Exception {
        var list = List.of(
                new CategoryAddDTO("6361625ffb716e1c2b2dc202", "6325ee332088c03f152df342"),
                new CategoryAddDTO("63628e0f6eebe000c3dc798b", "6325ee332088c03f152df342")
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/auth_info/addCategory", var,
                        Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void addCategoryMethodIsBadRequestTest() throws Exception {
        var list = List.of(
                new CategoryAddDTO("6361625ffb716e1c2b2dc20", "6325ee332088c03f152df342"),
                new CategoryAddDTO("$56ty7", "6325ee332088c03f152df342"),
                new CategoryAddDTO("6361625ffb716e1c2b2dc202", "46tghfds"),
                new CategoryAddDTO("r564t2", "fug"),
                new CategoryAddDTO("", ""),
                new CategoryAddDTO("6361625ffb716e1c2b2dc202", "6325ee332088c03f152df321")
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/auth_info/addCategory", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void addCategoryMethodIsNotFoundTest() throws Exception {
        var list = List.of(
                new CategoryAddDTO("6361625ffb716e1c2b2dc201", "6325ee332088c03f152df342"),
                new CategoryAddDTO("6361625ffb716e1c2b2dc203", "6325ee332088c03f152df321")
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/auth_info/addCategory", var,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void addAllCategoryMethodIsOkTest() throws Exception {
        var list = List.of(
                new CategoryAddAllDTO("63628e0f6eebe000c3dc798c", List.of("6325ee332088c03f152df330", "6325ee332088c03f152df331")),
                new CategoryAddAllDTO("63628e0f6eebe000c3dc798d", List.of("6325ee332088c03f152df330", "6325ee332088c03f152df331")),
                new CategoryAddAllDTO("63628e0f6eebe000c3dc798c", List.of("6325ee332088c03f152df332", "6325ee332088c03f152df333")),
                new CategoryAddAllDTO("63628e0f6eebe000c3dc798d", List.of("6325ee332088c03f152df332", "6325ee332088c03f152df333"))
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/auth_info/addAllCategory", var,
                        Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void addAllCategoryMethodIsBadRequestTest() throws Exception {
        var list = List.of(
                new CategoryAddAllDTO("63628e0f6eebe000c3dc798", List.of("6325ee332088c03f152df330", "6325ee332088c03f152df331")),
                new CategoryAddAllDTO("63628e0f6eebe000c3dc798d", List.of("6325ee332088c03f152df33f", "gfdgdf")),
                new CategoryAddAllDTO("63628e0f6eebe000c3dc798d", List.of("784tguy", "6325ee332088c03f152df33f")),
                new CategoryAddAllDTO("fsgufgsyu", List.of("784tguy", "6325ee332088c03f152df33f")),
                new CategoryAddAllDTO("_fyudgyuf", List.of("784tguy", "6783u")),
                new CategoryAddAllDTO("15673522gu", List.of("784tguy", "fdgretre")),
                new CategoryAddAllDTO("", List.of("", "")),
                new CategoryAddAllDTO("rgfhu", List.of("6325ee332088c03f152df332", "gfudgy")),
                new CategoryAddAllDTO("63628e0f6eebe000c3dc798d", List.of("6325ee332088c03f152df13a", "6325ee332088c03f152df333"))
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/auth_info/addAllCategory", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void addAllCategoryMethodIsNotFoundTest() throws Exception {
        var list = List.of(
                new CategoryAddAllDTO("6320c3daef1ded597035d891", List.of("6325ee332088c03f152df330", "6325ee332088c03f152df331"))
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/auth_info/addAllCategory", var,
                        Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void deleteCategoryMethodIsNoContentTest() throws Exception {
        var list = List.of(
                new CategoryDeleteDTO("63628e0f6eebe000c3dc798d", "6325ee332088c03f152df330")
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessiveDeleteObj("/api/v1/auth_info/deleteCategory", var,
                        Utils.matchers(Utils.StatusChoice.NO_CONTENT), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void deleteCategoryMethodIsBadRequestTest() throws Exception {
        var list = List.of(
                new CategoryDeleteDTO("63628e0f6eebe000c3dc798", "6325ee332088c03f152df330"),
                new CategoryDeleteDTO("63628e0f6eebe000c3dc798d", "6325ee332088c03f152df33"),
                new CategoryDeleteDTO("r784h", "6325ee332088c03f152df330"),
                new CategoryDeleteDTO("289014", "43822432"),
                new CategoryDeleteDTO("", ""),
                new CategoryDeleteDTO("63628e0f6eebe000c3dc798d", "4g8h")
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessiveDeleteObj("/api/v1/auth_info/deleteCategory", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void deleteCategoryMethodIsNotFoundTest() throws Exception {
        var var = new CategoryDeleteDTO("63628e0f6eebe000c3dc798a", "6325ee332088c03f152df330");
        autoMockMvc.obsessiveDeleteObj("/api/v1/auth_info/deleteCategory", var,
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());

    }

    @Test
    public void deleteAllCategoryMethodIsNoContentTest() throws Exception {
        var var = new CategoryDeleteAllDTO("63628e0f6eebe000c3dc798c", List.of("6325ee332088c03f152df330"));
        autoMockMvc.obsessiveDeleteObj("/api/v1/auth_info/deleteAllCategory", var,
                Utils.matchers(Utils.StatusChoice.NO_CONTENT), MockMvcResultHandlers.print());

    }

    @Test
    public void deleteAllCategoryMethodIsBadRequestTest() throws Exception {
        var list = List.of(
                new CategoryDeleteAllDTO("63628e0f6eebe000c3dc798", List.of("6325ee332088c03f152df330")),
                new CategoryDeleteAllDTO("63628e0f6eebe000c3dc798d", List.of("6325ee332088c03f152df3")),
                new CategoryDeleteAllDTO("r784h", List.of("6325ee332088c03f152df330")),
                new CategoryDeleteAllDTO("289014", List.of("43822432")),
                new CategoryDeleteAllDTO("", List.of("")),
                new CategoryDeleteAllDTO("63628e0f6eebe000c3dc798d", List.of("4g8h"))
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessiveDeleteObj("/api/v1/auth_info/deleteAllCategory", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void deleteAllCategoryMethodIsNotFoundTest() throws Exception {
        var var = new CategoryDeleteAllDTO("63628e0f6eebe000c3dc798a", List.of("6325ee332088c03f152df330"));
        autoMockMvc.obsessiveDeleteObj("/api/v1/auth_info/deleteAllCategory", var,
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
    }

    @Test
    public void deleteMethodIsNoContentTest() throws Exception {
        var var = "63628e0f6eebe000c3dc798d";
        autoMockMvc.obsessiveDelete("/api/v1/auth_info/delete/{id}", var,
                Utils.matchers(Utils.StatusChoice.NO_CONTENT), MockMvcResultHandlers.print());
    }

    @Test
    public void deleteMethodIsBadRequestTest() throws Exception {
        var list = List.of(
                "fydsfgdsuyfsfs",
                "63628e0f6eebe000c3dc798",
                "3628e0f6eebe000c3dc798",
                "yfuhi763"
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessiveDelete("/api/v1/auth_info/delete/{id}", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void deleteMethodIsNotFoundTest() throws Exception {
        var var = "63628e0f6eebe000c3dc798d";
        autoMockMvc.obsessiveDelete("/api/v1/auth_info/delete/{id}", var,
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
    }

    @Test
    public void changeVisibilityMethodIsOkTest() throws Exception {
        var var = new AuthInfoChangeVisibilityDTO("63628e0f6eebe000c3dc798b", AuthInfoVisibilityEnum.PRIVATE);
        autoMockMvc.obsessivePut("/api/v1/auth_info/changeVisibility", var,
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void changeVisibilityMethodIsBadRequestTest() throws Exception {
        var list = List.of(
                new AuthInfoChangeVisibilityDTO("567348h", AuthInfoVisibilityEnum.PRIVATE),
                new AuthInfoChangeVisibilityDTO("34fhn", AuthInfoVisibilityEnum.PRIVATE),
                new AuthInfoChangeVisibilityDTO("483h", AuthInfoVisibilityEnum.PRIVATE),
                new AuthInfoChangeVisibilityDTO("8392138", AuthInfoVisibilityEnum.PRIVATE)
        );
        list.forEach((var) -> {
            try {
                autoMockMvc.obsessivePut("/api/v1/auth_info/changeVisibility", var,
                        Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void changeVisibilityMethodIsNotFoundTest() throws Exception {
        var var = new AuthInfoChangeVisibilityDTO("63628e0f6eebe000c3dc798a", AuthInfoVisibilityEnum.PRIVATE);
        autoMockMvc.obsessivePut("/api/v1/auth_info/changeVisibility", var,
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
    }


    @Test
    public void detailMethodIsOkTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_info/detail/6361625ffb716e1c2b2dc202",
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void detailMethodIsBadRequestTest() {
        List.of("1", "fydgytfdsfdsfds", "forbu")
                .forEach((elem) -> {
                    try {
                        autoMockMvc.obsessiveGet("/api/v1/auth_info/detail/{id}", elem,
                                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void detailMethodIsNotFoundTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_info/detail/634975aae4d30c6c6b066de1",
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());

    }

    @Test
    public void getMethodIsOkTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_info/get/6361625ffb716e1c2b2dc202",
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void getMethodIsBadRequestTest() {
        List.of("1", "fydgytfdsfdsfds", "forbu")
                .forEach((elem) -> {
                    try {
                        autoMockMvc.obsessiveGet("/api/v1/auth_info/get/{id}", elem,
                                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void getMethodIsNotFoundTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_info/get/633568fb6f2f9218191858d1",
                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());

    }

    @Test
    public void getCategoryListMethodIsOkTest() throws Exception {
        autoMockMvc.obsessiveGet("/api/v1/auth_info/getCategoryList/{id}", "6361625ffb716e1c2b2dc202",
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

    @Test
    public void getCategoryListMethodIsBadRequestTest() throws Exception {
        List.of("1", "fydgytfdsfdsfds", "$fdusfds")
                .forEach((elem) -> {
                    try {
                        autoMockMvc.obsessiveGet("/api/v1/auth_info/getCategoryList/{id}", elem,
                                Utils.matchers(Utils.StatusChoice.BAD_REQUEST), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void getCategoryListMethodIsNotFoundTest() throws Exception {
        List.of("6361625ffb716e1c2b2dc201", "6361625ffb716e1c2b2dc203", "6361625ffb716e1c2b2dc204")
                .forEach((elem) -> {
                    try {
                        autoMockMvc.obsessiveGet("/api/v1/auth_info/getCategoryList/{id}", elem,
                                Utils.matchers(Utils.StatusChoice.NOT_FOUND), MockMvcResultHandlers.print());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    public void listMethodIsOkTest() throws Exception {
        LinkedMultiValueMap<String, String> map = Utils.MAP;
        map.add("fieldsEnum", "PHONE_NUMBER");
        autoMockMvc.obsessiveGet("/api/v1/auth_info/list", map,
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }


}
