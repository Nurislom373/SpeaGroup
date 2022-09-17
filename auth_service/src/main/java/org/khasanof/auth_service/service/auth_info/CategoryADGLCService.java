package org.khasanof.auth_service.service.auth_info;

import org.khasanof.auth_service.dto.category.CategoryGetDTO;

import java.util.List;

public interface CategoryADGLCService {

    void addCategory(String infoId, String categoryId);

    void addAllCategory(String infoId, List<String> categoryIds);

    void deleteCategory(String infoId, String categoryId);

    void deleteAllCategory(String infoId, List<String> categoryIds);

    CategoryGetDTO getCategory(String infoId, String categoryId);

    List<CategoryGetDTO> listCategory(String infoId);

    int count(String infoId);

}
