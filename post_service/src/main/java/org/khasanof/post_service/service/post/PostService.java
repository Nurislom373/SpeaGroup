package org.khasanof.post_service.service.post;

import org.khasanof.post_service.criteria.post.PostCatCriteria;
import org.khasanof.post_service.criteria.post.PostCriteria;
import org.khasanof.post_service.criteria.post.PostRatingCriteria;
import org.khasanof.post_service.dto.post.*;
import org.khasanof.post_service.service.BaseService;
import org.khasanof.post_service.service.GenericCUDService;
import org.khasanof.post_service.service.GenericGDLService;

import java.util.List;

public interface PostService extends GenericCUDService<PostCreateDTO, PostUpdateDTO, String>,
        GenericGDLService<PostGetDTO, PostDetailDTO, String, PostCriteria>,
        BaseService {

    List<PostGetDTO> getAllWithCreatedBy(String userId);

    List<PostGetDTO> listWithCategory(PostCatCriteria catCriteria);

    List<PostGetDTO> listWithRating(PostRatingCriteria ratingCriteria);

    PostDetWComDTO getByPostIdDetailAndComments(String id, Integer count);

    boolean existById(String postId);

    boolean existByIdAndCheckBlocked(String postId);

}
