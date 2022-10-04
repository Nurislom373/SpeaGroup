package org.khasanof.post_service.service.category;

import org.khasanof.post_service.criteria.category.CategoryCriteria;
import org.khasanof.post_service.dto.category.CategoryCreateDTO;
import org.khasanof.post_service.dto.category.CategoryDetailDTO;
import org.khasanof.post_service.dto.category.CategoryGetDTO;
import org.khasanof.post_service.dto.category.CategoryUpdateDTO;
import org.khasanof.post_service.service.BaseService;
import org.khasanof.post_service.service.GenericCUDService;
import org.khasanof.post_service.service.GenericGDLService;

public interface CategoryService extends GenericCUDService<CategoryCreateDTO, CategoryUpdateDTO, String>,
        GenericGDLService<CategoryGetDTO, CategoryDetailDTO, String, CategoryCriteria>,
        BaseService {
}
