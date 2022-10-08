package org.khasanof.post_service.service.post_rating;

import org.khasanof.post_service.criteria.post_rating.PostRatingCriteria;
import org.khasanof.post_service.dto.post_rating.PostRatingCreateDTO;
import org.khasanof.post_service.dto.post_rating.PostRatingDetailDTO;
import org.khasanof.post_service.dto.post_rating.PostRatingGetDTO;
import org.khasanof.post_service.service.BaseService;
import org.khasanof.post_service.service.GenericGDLService;

public interface PostRatingService extends GenericGDLService<PostRatingGetDTO, PostRatingDetailDTO, String, PostRatingCriteria>, BaseService {

    void create(PostRatingCreateDTO dto);

    void delete(String postId);

}
