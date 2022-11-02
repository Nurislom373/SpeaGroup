package org.khasanof.auth_service.service.auth_info;

import org.khasanof.auth_service.dto.category.CategoryFindAllRequestDTO;
import org.khasanof.auth_service.dto.category.CategoryDetailDTO;
import org.khasanof.auth_service.response.Data;
import org.khasanof.auth_service.utils.BaseUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "PostCategoryFeignClient", url = "http://localhost:8802", path = BaseUtils.PATH + "/category")
public interface PostCategoryFeignClient {

    @PostMapping("/findAllById")
    Data<List<CategoryDetailDTO>> findAllById(@RequestBody CategoryFindAllRequestDTO dto);

    @GetMapping("/get/{id}")
    Data<CategoryDetailDTO> get(@PathVariable String id);

}
