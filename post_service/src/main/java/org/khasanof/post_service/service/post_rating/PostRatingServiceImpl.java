package org.khasanof.post_service.service.post_rating;

import org.khasanof.post_service.criteria.post_rating.PostRatingCriteria;
import org.khasanof.post_service.dto.post_rating.PostRatingCreateDTO;
import org.khasanof.post_service.dto.post_rating.PostRatingDetailDTO;
import org.khasanof.post_service.dto.post_rating.PostRatingGetDTO;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.entity.post_rating.PostRatingEntity;
import org.khasanof.post_service.mapper.post.PostMapper;
import org.khasanof.post_service.mapper.post_rating.PostRatingMapper;
import org.khasanof.post_service.repository.post_rating.PostRatingRepository;
import org.khasanof.post_service.service.AbstractService;
import org.khasanof.post_service.service.post.PostService;
import org.khasanof.post_service.service.post_like.PostLikeService;
import org.khasanof.post_service.validator.post_rating.PostRatingValidator;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Objects;

@Service
public class PostRatingServiceImpl extends AbstractService<PostRatingRepository, PostRatingMapper, PostRatingValidator> implements PostRatingService {

    private final PostService postService;
    private final PostLikeService likeService;
    private final PostMapper postMapper;

    public PostRatingServiceImpl(PostRatingRepository repository, PostRatingMapper mapper, PostRatingValidator validator, PostService postService, PostLikeService likeService, PostMapper postMapper) {
        super(repository, mapper, validator);
        this.postService = postService;
        this.likeService = likeService;
        this.postMapper = postMapper;
    }

    @Override
    public void create(PostRatingCreateDTO dto) {
//        TODO write rating create logic when view and save service finish
        validator.validCreateDTO(dto);
        if (repository.existsById(dto.getRatingPostId())) {
            throw new RuntimeException("Post Rating Already Created with Id");
        }
        PostRatingEntity postRatingEntity = mapper.toCreateDTO(dto);
        PostEntity postEntity = postMapper.toGetDTO(postService.get(dto.getRatingPostId()));
        if (Objects.isNull(postEntity)) {
            throw new NotFoundException("Post not found");
        }
        postRatingEntity.setPostId(postEntity);
//        likeService.get()
//        repository.save()
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
