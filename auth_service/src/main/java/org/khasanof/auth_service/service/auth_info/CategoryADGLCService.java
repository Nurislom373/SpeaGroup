package org.khasanof.auth_service.service.auth_info;

import org.khasanof.auth_service.dto.category.*;

import java.util.List;

public interface CategoryADGLCService {

    void addCategory(CategoryAddDTO dto);

    void addAllCategory(CategoryAddAllDTO dto);

    void deleteCategory(CategoryDeleteDTO dto);

    void deleteAllCategory(CategoryDeleteAllDTO dto);

    CategoryDetailDTO getCategory(String infoId, String categoryId);

    List<CategoryDetailDTO> listCategory(String infoId);

    int categoriesCount(String infoId);

}
