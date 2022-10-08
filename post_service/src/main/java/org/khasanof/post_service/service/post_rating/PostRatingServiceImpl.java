package org.khasanof.post_service.service.post_rating;

import org.khasanof.post_service.criteria.post_rating.PostRatingCriteria;
import org.khasanof.post_service.dto.post_rating.PostRatingCreateDTO;
import org.khasanof.post_service.dto.post_rating.PostRatingDetailDTO;
import org.khasanof.post_service.dto.post_rating.PostRatingGetDTO;
import org.khasanof.post_service.mapper.post_rating.PostRatingMapper;
import org.khasanof.post_service.repository.post_rating.PostRatingRepository;
import org.khasanof.post_service.service.AbstractService;
import org.khasanof.post_service.validator.post_rating.PostRatingValidator;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class PostRatingServiceImpl extends AbstractService<PostRatingRepository, PostRatingMapper, PostRatingValidator> implements PostRatingService {

    public PostRatingServiceImpl(PostRatingRepository repository, PostRatingMapper mapper, PostRatingValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public void create(PostRatingCreateDTO dto) {
        validator.validCreateDTO(dto);
        if (repository.existsById(dto.getRatingPostId())) {
            throw new RuntimeException("Post Rating Already Created with Id");
        }

    }

    @Override
    public void delete(String postId) {
        validator.validKey(postId);
    }

    @Override
    public PostRatingGetDTO get(String id) {
        return null;
    }

    @Override
    public PostRatingDetailDTO detail(String id) {
        return null;
    }

    @Override
    public List<PostRatingGetDTO> list(PostRatingCriteria criteria) {
        return null;
    }
}
