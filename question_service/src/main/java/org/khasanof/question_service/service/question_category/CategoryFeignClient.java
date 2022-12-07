package org.khasanof.question_service.service.question_category;

import org.khasanof.question_service.dto.category.CategoryDetailDTO;
import org.khasanof.question_service.dto.category.CategoryFindAllRequestDTO;
import org.khasanof.question_service.response.Data;
import org.khasanof.question_service.utils.BaseUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/7/2022
 * <br/>
 * Time: 10:57 PM
 * <br/>
 * Package: org.khasanof.question_service.service.question_category
 */
@FeignClient(name = "CategoryFeignClient", url = "http://localhost:8802", path = BaseUtils.PATH + "/category")
public interface CategoryFeignClient {

    @RequestMapping(value = "/findAllById", method = RequestMethod.POST)
    Data<List<CategoryDetailDTO>> findAllById(@RequestBody CategoryFindAllRequestDTO dto) throws Exception;

}
