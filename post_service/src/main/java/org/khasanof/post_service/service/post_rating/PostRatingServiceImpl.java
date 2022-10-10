package org.khasanof.post_service.service.post_rating;

import org.khasanof.post_service.criteria.post_rating.PostRatingCriteria;
import org.khasanof.post_service.dto.post_like.PostLikeGetDTO;
import org.khasanof.post_service.dto.post_rating.PostRatingCreateDTO;
import org.khasanof.post_service.dto.post_rating.PostRatingDetailDTO;
import org.khasanof.post_service.dto.post_rating.PostRatingGetDTO;
import org.khasanof.post_service.dto.post_save.PostSaveGetDTO;
import org.khasanof.post_service.dto.post_share.PostShareGetDTO;
import org.khasanof.post_service.dto.post_view.PostViewGetDTO;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.entity.post_rating.PostRatingEntity;
import org.khasanof.post_service.enums.rating.RatingPointEnum;
import org.khasanof.post_service.mapper.post.PostMapper;
import org.khasanof.post_service.mapper.post_rating.PostRatingMapper;
import org.khasanof.post_service.repository.post_rating.PostRatingRepository;
import org.khasanof.post_service.service.AbstractService;
import org.khasanof.post_service.service.post.PostService;
import org.khasanof.post_service.service.post_like.PostLikeService;
import org.khasanof.post_service.service.post_save.PostSaveService;
import org.khasanof.post_service.service.post_share.PostShareService;
import org.khasanof.post_service.service.post_view.PostViewService;
import org.khasanof.post_service.validator.post_rating.PostRatingValidator;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PostRatingServiceImpl extends AbstractService<PostRatingRepository, PostRatingMapper, PostRatingValidator> implements PostRatingService {

    private final PostService postService;
    private final PostLikeService likeService;
    private final PostViewService viewService;
    private final PostShareService postShareService;
    private final PostSaveService saveService;
    private final PostMapper postMapper;

    public PostRatingServiceImpl(PostRatingRepository repository, PostRatingMapper mapper, PostRatingValidator validator, PostService postService, PostLikeService likeService, PostViewService viewService, PostShareService postShareService, PostSaveService saveService, PostMapper postMapper) {
        super(repository, mapper, validator);
        this.postService = postService;
        this.likeService = likeService;
        this.viewService = viewService;
        this.postShareService = postShareService;
        this.saveService = saveService;
        this.postMapper = postMapper;
    }

    @Override
    public void create(PostRatingCreateDTO dto) {
        // TODO write rating create logic when view and save service finish
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
        PostLikeGetDTO likeGetDTO = likeService.getByPostId(dto.getRatingPostId());
        postRatingEntity.setLikesCount(Objects.isNull(likeGetDTO.getLikesCount()) ? 0 : likeGetDTO.getLikesCount());
        PostViewGetDTO viewGetDTO = viewService.getByPostId(dto.getRatingPostId());
        postRatingEntity.setViewsCount(Objects.isNull(viewGetDTO.getViewsCount()) ? 0 : viewGetDTO.getViewsCount());
        PostShareGetDTO shareGetDTO = postShareService.getByPostId(dto.getRatingPostId());
        postRatingEntity.setSharesCount(Objects.isNull(shareGetDTO.getSharesCount()) ? 0 : shareGetDTO.getSharesCount());
        PostSaveGetDTO saveGetDTO = saveService.getByPostId(dto.getRatingPostId());
        postRatingEntity.setSavesCount(Objects.isNull(saveGetDTO.getSaveCount()) ? 0 : saveGetDTO.getSaveCount());
        repository.save(postRatingEntity);
    }

    public void setRating(PostRatingEntity entity) {
        Integer likesCount = entity.getLikesCount();
        Integer sharesCount = entity.getSharesCount();
        Integer viewsCount = entity.getViewsCount();
        Integer savesCount = entity.getSavesCount();
        Date postDate = Date.from(entity.getCreatedAt());
        Date nowDate = Date.from(Instant.now());
        Integer totalLikePoint = likesCount * RatingPointEnum.LIKE.getPoint();
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
