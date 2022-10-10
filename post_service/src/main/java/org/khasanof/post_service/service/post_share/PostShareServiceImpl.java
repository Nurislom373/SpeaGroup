package org.khasanof.post_service.service.post_share;

import org.khasanof.post_service.criteria.post_share.PostShareCriteria;
import org.khasanof.post_service.dto.post_share.PostShareCreateDTO;
import org.khasanof.post_service.dto.post_share.PostShareDetailDTO;
import org.khasanof.post_service.dto.post_share.PostShareGetDTO;
import org.khasanof.post_service.mapper.post_share.PostShareMapper;
import org.khasanof.post_service.repository.post_share.PostShareRepository;
import org.khasanof.post_service.service.AbstractService;
import org.khasanof.post_service.validator.post_share.PostShareValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostShareServiceImpl extends AbstractService<PostShareRepository, PostShareMapper, PostShareValidator> implements PostShareService {

    public PostShareServiceImpl(PostShareRepository repository, PostShareMapper mapper, PostShareValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public void create(PostShareCreateDTO dto) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public PostShareGetDTO get(String id) {
        return null;
    }

    @Override
    public PostShareDetailDTO detail(String id) {
        return null;
    }

    @Override
    public List<PostShareGetDTO> list(PostShareCriteria criteria) {
        return null;
    }
}
