package org.khasanof.post_service.service.post_share;

import org.khasanof.post_service.criteria.post_share.PostShareCriteria;
import org.khasanof.post_service.dto.post_share.PostShareCreateDTO;
import org.khasanof.post_service.dto.post_share.PostShareDetailDTO;
import org.khasanof.post_service.dto.post_share.PostShareGetDTO;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.entity.post_share.PostShareEntity;
import org.khasanof.post_service.entity.share.ShareEntity;
import org.khasanof.post_service.mapper.post.PostMapper;
import org.khasanof.post_service.mapper.post_share.PostShareMapper;
import org.khasanof.post_service.repository.post_share.PostShareRepository;
import org.khasanof.post_service.service.AbstractService;
import org.khasanof.post_service.service.post.PostService;
import org.khasanof.post_service.validator.post_share.PostShareValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PostShareServiceImpl extends AbstractService<PostShareRepository, PostShareMapper, PostShareValidator> implements PostShareService {

    private final PostService postService;
    private final PostMapper postMapper;

    public PostShareServiceImpl(PostShareRepository repository, PostShareMapper mapper, PostShareValidator validator, PostService postService, PostMapper postMapper) {
        super(repository, mapper, validator);
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @Override
    public void create(PostShareCreateDTO dto) {
        validator.validCreateDTO(dto);
        Optional<PostShareEntity> optional = repository.findByPostIdQuery(dto.getSharePostId());
        if (optional.isPresent()) {
            PostShareEntity postShareEntity = optional.get();
            LinkedList<ShareEntity> shares = postShareEntity.getShares();
            shares.add(new ShareEntity(dto.getUserId(), dto.getType()));
            postShareEntity.setShares(shares);
            postShareEntity.setUpdatedAt(Instant.now());
            postShareEntity.setUpdatedBy("-1");
            repository.save(postShareEntity);
        } else {
            if (postService.existByIdAndCheckBlocked(dto.getSharePostId())) {
                throw new RuntimeException("This Post is block");
            }
            PostEntity postEntity = postMapper.toGetDTO(postService.get(dto.getSharePostId()));
            PostShareEntity postShareEntity = mapper.toCreateDTO(dto);
            postShareEntity.setPostId(postEntity);
            LinkedList<ShareEntity> shares = postShareEntity.getShares();
            shares.add(new ShareEntity(dto.getUserId(), dto.getType()));
            postShareEntity.setShares(shares);
            repository.save(postShareEntity);
        }
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id)) {
            throw new NotFoundException("Post Share not found");
        }
        repository.deleteById(id);
    }

    @Override
    public PostShareGetDTO getByPostId(String id) {
        validator.validKey(id);
        return returnToGetDTO(
                repository.findByPostIdQuery(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post Share not found");
                        })
        );
    }

    @Override
    public PostShareGetDTO get(String id) {
        validator.validKey(id);
        return returnToGetDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post Share not found");
                        })
        );
    }

    @Override
    public PostShareDetailDTO detail(String id) {
        validator.validKey(id);
        return mapper.fromDetailDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post Share not found");
                        })
        );
    }

    @Override
    public List<PostShareGetDTO> list(PostShareCriteria criteria) {
        return repository.findAll(
                        PageRequest.of(criteria.getPage(), criteria.getSize())
                )
                .stream()
                .map(this::returnToGetDTO)
                .toList();
    }

    private PostShareGetDTO returnToGetDTO(PostShareEntity entity) {
        PostShareGetDTO postShareGetDTO = mapper.fromGetDTO(entity);
        postShareGetDTO.setSharePostId(entity.getPostId().getId());
        postShareGetDTO.setSharesCount(entity.getShares().size());
        return postShareGetDTO;
    }
}
