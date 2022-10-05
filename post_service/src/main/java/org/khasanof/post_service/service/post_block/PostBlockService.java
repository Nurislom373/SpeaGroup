package org.khasanof.post_service.service.post_block;

import org.khasanof.post_service.criteria.post_block.PostBlockCriteria;
import org.khasanof.post_service.dto.post_block.PostBlockCreateDTO;
import org.khasanof.post_service.dto.post_block.PostBlockDetailDTO;
import org.khasanof.post_service.dto.post_block.PostBlockGetDTO;
import org.khasanof.post_service.dto.post_block.PostBlockUpdateDTO;
import org.khasanof.post_service.service.BaseService;
import org.khasanof.post_service.service.GenericCUDService;
import org.khasanof.post_service.service.GenericGDLService;

public interface PostBlockService extends GenericCUDService<PostBlockCreateDTO, PostBlockUpdateDTO, String>,
        GenericGDLService<PostBlockGetDTO, PostBlockDetailDTO, String, PostBlockCriteria>,
        BaseService {
}
