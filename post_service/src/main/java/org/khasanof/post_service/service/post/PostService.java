package org.khasanof.post_service.service.post;

import org.khasanof.post_service.criteria.post.PostCriteria;
import org.khasanof.post_service.dto.post.PostCreateDTO;
import org.khasanof.post_service.dto.post.PostDetailDTO;
import org.khasanof.post_service.dto.post.PostGetDTO;
import org.khasanof.post_service.dto.post.PostUpdateDTO;
import org.khasanof.post_service.service.BaseService;
import org.khasanof.post_service.service.GenericCUDService;
import org.khasanof.post_service.service.GenericGDLService;

import java.util.List;

public interface PostService extends GenericCUDService<PostCreateDTO, PostUpdateDTO, String>,
        GenericGDLService<PostGetDTO, PostDetailDTO, String, PostCriteria>,
        BaseService {

    List<PostGetDTO> getAllWithCreatedBy(String userId);

    boolean existById(String postId);

    boolean existByIdAndCheckBlocked(String postId);

}
