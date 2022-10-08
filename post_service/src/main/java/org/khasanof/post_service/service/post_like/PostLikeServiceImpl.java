package org.khasanof.post_service.service.post_like;

import org.khasanof.post_service.criteria.post_like.PostLikeCriteria;
import org.khasanof.post_service.dto.post_like.PostLikeCreateDTO;
import org.khasanof.post_service.dto.post_like.PostLikeDeleteDTO;
import org.khasanof.post_service.dto.post_like.PostLikeDetailDTO;
import org.khasanof.post_service.dto.post_like.PostLikeGetDTO;
import org.khasanof.post_service.entity.like.LikeEntity;
import org.khasanof.post_service.entity.post_like.PostLikeEntity;
import org.khasanof.post_service.enums.like.LikeTypeEnum;
import org.khasanof.post_service.mapper.post_like.PostLikeMapper;
import org.khasanof.post_service.repository.post_like.PostLikeRepository;
import org.khasanof.post_service.service.AbstractService;
import org.khasanof.post_service.service.post.PostService;
import org.khasanof.post_service.validator.post_like.PostLikeValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class PostLikeServiceImpl extends AbstractService<PostLikeRepository, PostLikeMapper, PostLikeValidator> implements PostLikeService {

    private final PostService postService;

    public PostLikeServiceImpl(PostLikeRepository repository, PostLikeMapper mapper, PostLikeValidator validator, PostService postService) {
        super(repository, mapper, validator);
        this.postService = postService;
    }

    @Override
    public void create(PostLikeCreateDTO dto) {
        validator.validCreateDTO(dto);
        if (!postService.existByIdAndCheckBlocked(dto.getLikePostId())) {
            throw new NotFoundException("Post not found");
        }
        PostLikeEntity likeEntity = repository.findByQuery(dto.getLikePostId());
        if (!LikeTypeEnum.hasLikeType(dto.getType())) {
            throw new NotFoundException("Like type Invalid");
        }
        if (Objects.isNull(likeEntity)) {
            PostLikeEntity postLikeEntity = mapper.toCreateDTO(dto);
            LinkedList<LikeEntity> likes = postLikeEntity.getLikes();
            likes.add(new LikeEntity(dto.getUserId(), dto.getType()));
            postLikeEntity.setLikes(likes);
            repository.save(postLikeEntity);
        } else {
            LinkedList<LikeEntity> likes = likeEntity.getLikes();
            likes.add(new LikeEntity(dto.getUserId(), dto.getType()));
            likeEntity.setLikes(likes);
            repository.save(likeEntity);
        }
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id)) {
            throw new NotFoundException("Post Like not found");
        }
        repository.deleteById(id);
    }

    @Override
    public void deleteLike(PostLikeDeleteDTO dto) {
        validator.validDeleteDTO(dto);
        if (!postService.existById(dto.getLikePostId())) {
            throw new NotFoundException("Post not found");
        }
        PostLikeEntity likeEntity = repository.findByQuery(dto.getLikePostId());
        if (Objects.isNull(likeEntity)) {
            throw new NotFoundException("Post Like not found");
        }
        LinkedList<LikeEntity> likes = likeEntity.getLikes();
        if (!likes.removeIf(l -> l.getUserId().equals(dto.getUserId()))) {
            throw new NotFoundException("Like not found with UserId -> " + dto.getUserId());
        }
        likeEntity.setLikes(likes);
        repository.save(likeEntity);
    }

    @Override
    public PostLikeGetDTO get(String id) {
        validator.validKey(id);
        return entityToGetDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post Like not found");
                        })
        );
    }

    @Override
    public PostLikeDetailDTO detail(String id) {
        validator.validKey(id);
        return mapper.fromDetailDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post Like not found");
                        })
        );
    }

    @Override
    public List<PostLikeGetDTO> list(PostLikeCriteria criteria) {
        return repository.findAll(
                        PageRequest.of(
                                criteria.getPage(), criteria.getSize()
                        )).stream()
                .map(this::entityToGetDTO)
                .toList();
    }

    @Override
    public long count(String postId) {
        validator.validKey(postId);
        return repository.findById(postId).orElseThrow(() -> {
            throw new NotFoundException("Post Like not found");
        }).getLikes().size();
    }

    private PostLikeGetDTO entityToGetDTO(PostLikeEntity entity) {
        PostLikeGetDTO postLikeGetDTO = mapper.fromGetDTO(entity);
        postLikeGetDTO.setLikePostId(entity.getPostId().getId());
        postLikeGetDTO.setLikesCount(entity.getLikes().size());
        return postLikeGetDTO;
    }
}
