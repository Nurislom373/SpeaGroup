package org.khasanof.post_service.service.post_like;

import org.khasanof.post_service.dto.post_like.PostLikeCreateDTO;
import org.khasanof.post_service.dto.post_like.PostLikeDeleteDTO;

public interface PostLikeCDCService {

    void create(PostLikeCreateDTO dto);

    void delete(String id);

    void deleteLike(PostLikeDeleteDTO dto);

    long count(String postId);

}
