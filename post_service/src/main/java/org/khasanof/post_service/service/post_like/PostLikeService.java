package org.khasanof.post_service.service.post_like;

import org.khasanof.post_service.criteria.post_like.PostLikeCriteria;
import org.khasanof.post_service.dto.post_like.PostLikeDetailDTO;
import org.khasanof.post_service.dto.post_like.PostLikeGetDTO;
import org.khasanof.post_service.service.BaseService;
import org.khasanof.post_service.service.GenericGDLService;

public interface PostLikeService extends GenericGDLService<PostLikeGetDTO, PostLikeDetailDTO, String, PostLikeCriteria>,
        PostLikeCDCService,
        BaseService {
}
