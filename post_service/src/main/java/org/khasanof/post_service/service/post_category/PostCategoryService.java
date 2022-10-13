package org.khasanof.post_service.service.post_category;

import org.khasanof.post_service.criteria.post_category.PostCategoryCriteria;
import org.khasanof.post_service.dto.post_category.PostCategoryAddAllDTO;
import org.khasanof.post_service.dto.post_category.PostCategoryAddDTO;
import org.khasanof.post_service.dto.post_category.PostCategoryDetailDTO;
import org.khasanof.post_service.dto.post_category.PostCategoryGetDTO;
import org.khasanof.post_service.service.BaseService;
import org.khasanof.post_service.service.GenericGDLService;

public interface PostCategoryService extends GenericGDLService<PostCategoryGetDTO, PostCategoryDetailDTO, String, PostCategoryCriteria>, BaseService {

    void addCategory(PostCategoryAddDTO dto);

    void addAllCategory(PostCategoryAddAllDTO dto);

    void delete(String id);

    void deleteCategory(String id, String categoryId);

}
