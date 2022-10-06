package org.khasanof.post_service.service.post_comment;

import org.khasanof.post_service.criteria.post_comment.PostCommentCriteria;
import org.khasanof.post_service.dto.post_comment.*;
import org.khasanof.post_service.service.BaseService;
import org.khasanof.post_service.service.GenericGDLService;

public interface PostCommentService extends GenericGDLService<PostCommentGetDTO, PostCommentDetailDTO, String, PostCommentCriteria>,
        PostCommentCDADService, BaseService {

}
