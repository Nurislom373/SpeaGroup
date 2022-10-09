package org.khasanof.post_service.service.post_save;

import org.khasanof.post_service.criteria.post_save.PostSaveCriteria;
import org.khasanof.post_service.dto.post_save.PostSaveCreateDTO;
import org.khasanof.post_service.dto.post_save.PostSaveDeleteDTO;
import org.khasanof.post_service.dto.post_save.PostSaveDetailDTO;
import org.khasanof.post_service.dto.post_save.PostSaveGetDTO;
import org.khasanof.post_service.service.BaseService;
import org.khasanof.post_service.service.GenericGDLService;

public interface PostSaveService extends GenericGDLService<PostSaveGetDTO, PostSaveDetailDTO, String, PostSaveCriteria>, BaseService {

    void create(PostSaveCreateDTO dto);

    void deleteSaved(PostSaveDeleteDTO dto);

    void delete(String id);

    PostSaveGetDTO getByPostId(String id);

}
