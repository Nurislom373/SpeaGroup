package org.khasanof.auth_service.service.category;

import org.khasanof.auth_service.criteria.category.CategoryCriteria;
import org.khasanof.auth_service.dto.category.CategoryCreateDTO;
import org.khasanof.auth_service.dto.category.CategoryGetDTO;
import org.khasanof.auth_service.dto.category.CategoryUpdateDTO;
import org.khasanof.auth_service.service.BaseService;
import org.khasanof.auth_service.service.GenericCUDService;

import java.util.List;

public interface CategoryService extends GenericCUDService<CategoryCreateDTO, CategoryUpdateDTO, String>, BaseService {

    List<CategoryGetDTO> list(CategoryCriteria criteria);

    CategoryGetDTO get(String id);

    long count();

}
