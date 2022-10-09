package org.khasanof.post_service.service.post_view;

import org.khasanof.post_service.criteria.post_view.PostViewCriteria;
import org.khasanof.post_service.dto.post_view.PostViewCreateDTO;
import org.khasanof.post_service.dto.post_view.PostViewDetailDTO;
import org.khasanof.post_service.dto.post_view.PostViewGetDTO;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.entity.post_view.PostViewEntity;
import org.khasanof.post_service.entity.view.ViewEntity;
import org.khasanof.post_service.mapper.post.PostMapper;
import org.khasanof.post_service.mapper.post_view.PostViewMapper;
import org.khasanof.post_service.repository.post_view.PostViewRepository;
import org.khasanof.post_service.service.AbstractService;
import org.khasanof.post_service.service.post.PostService;
import org.khasanof.post_service.validator.post_view.PostViewValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PostViewServiceImpl extends AbstractService<PostViewRepository, PostViewMapper, PostViewValidator> implements PostViewService {

    private final PostService postService;
    private final PostMapper postMapper;

    public PostViewServiceImpl(PostViewRepository repository, PostViewMapper mapper, PostViewValidator validator, PostService postService, PostMapper postMapper) {
        super(repository, mapper, validator);
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @Override
    public void create(PostViewCreateDTO dto) {
        validator.validCreateDTO(dto);
        Optional<PostViewEntity> optional = repository.findByPostIdQuery(dto.getViewPostId());
        if (optional.isPresent()) {
            PostViewEntity postViewEntity = optional.get();
            LinkedList<ViewEntity> views = postViewEntity.getViews();
            views.add(new ViewEntity(dto.getUserId()));
            postViewEntity.setViews(views);
            postViewEntity.setUpdatedAt(Instant.now());
            postViewEntity.setUpdatedBy("-1");
            repository.save(postViewEntity);
        } else {
            if (postService.existByIdAndCheckBlocked(dto.getViewPostId())) {
                throw new RuntimeException("This Post is block");
            }
            PostEntity postEntity = postMapper.toGetDTO(postService.get(dto.getViewPostId()));
            PostViewEntity postViewEntity = mapper.toCreateDTO(dto);
            postViewEntity.setPostId(postEntity);
            LinkedList<ViewEntity> views = postViewEntity.getViews();
            views.add(new ViewEntity(dto.getUserId()));
            postViewEntity.setViews(views);
            repository.save(postViewEntity);
        }
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id)) {
            throw new NotFoundException("Post View not found");
        }
        repository.deleteById(id);
    }

    @Override
    public PostViewGetDTO get(String id) {
        validator.validKey(id);
        return returnToGetDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post View not found");
                        })
        );
    }

    @Override
    public PostViewDetailDTO detail(String id) {
        validator.validKey(id);
        return mapper.fromDetailDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post View not found");
                        })
        );
    }

    @Override
    public List<PostViewGetDTO> list(PostViewCriteria criteria) {
        return repository.findAll(
                        PageRequest.of(criteria.getPage(), criteria.getSize())
                )
                .stream()
                .map(this::returnToGetDTO)
                .toList();
    }

    private PostViewGetDTO returnToGetDTO(PostViewEntity entity) {
        PostViewGetDTO postViewGetDTO = mapper.fromGetDTO(entity);
        postViewGetDTO.setViewPostId(entity.getPostId().getId());
        postViewGetDTO.setViewsCount(entity.getViews().size());
        return postViewGetDTO;
    }
}
