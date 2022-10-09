package org.khasanof.post_service.service.post_view;

import org.khasanof.post_service.criteria.post_view.PostViewCriteria;
import org.khasanof.post_service.dto.post_view.PostViewCreateDTO;
import org.khasanof.post_service.dto.post_view.PostViewDetailDTO;
import org.khasanof.post_service.dto.post_view.PostViewGetDTO;
import org.khasanof.post_service.service.BaseService;
import org.khasanof.post_service.service.GenericGDLService;

public interface PostViewService extends GenericGDLService<PostViewGetDTO, PostViewDetailDTO, String, PostViewCriteria>, BaseService {

    void create(PostViewCreateDTO dto);

    void delete(String id);

    PostViewGetDTO getByPostId(String id);

}
