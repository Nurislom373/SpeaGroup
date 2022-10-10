package org.khasanof.post_service.service.post_share;

import org.khasanof.post_service.criteria.post_share.PostShareCriteria;
import org.khasanof.post_service.dto.post_share.PostShareCreateDTO;
import org.khasanof.post_service.dto.post_share.PostShareDetailDTO;
import org.khasanof.post_service.dto.post_share.PostShareGetDTO;
import org.khasanof.post_service.service.BaseService;
import org.khasanof.post_service.service.GenericGDLService;

public interface PostShareService extends GenericGDLService<PostShareGetDTO, PostShareDetailDTO, String, PostShareCriteria>, BaseService {

    void create(PostShareCreateDTO dto);

    void delete(String id);

}
