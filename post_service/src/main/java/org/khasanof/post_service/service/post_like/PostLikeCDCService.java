package org.khasanof.post_service.service.post_like;

import org.khasanof.post_service.dto.post_like.PostLikeCreateDTO;
import org.khasanof.post_service.dto.post_like.PostLikeDeleteDTO;
import org.khasanof.post_service.dto.post_like.PostLikeGetDTO;
import org.khasanof.post_service.dto.post_like.PostLikeTypeCount;
import org.khasanof.post_service.entity.post.PostEntity;

public interface PostLikeCDCService {

    void create(PostEntity entity);

    void addLike(PostLikeCreateDTO dto);

    void delete(String id);

    void deleteLike(PostLikeDeleteDTO dto);

    long count(String postId);

    PostLikeTypeCount getByPostIdAndTypeLikeCount(String postId, String type);

    PostLikeGetDTO getByPostId(String id);

}
