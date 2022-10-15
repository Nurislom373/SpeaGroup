package org.khasanof.post_service.service.post_block;

import org.khasanof.post_service.criteria.post_block.PostBlockCriteria;
import org.khasanof.post_service.dto.post_block.PostBlockCreateDTO;
import org.khasanof.post_service.dto.post_block.PostBlockDetailDTO;
import org.khasanof.post_service.dto.post_block.PostBlockGetDTO;
import org.khasanof.post_service.dto.post_block.PostBlockUpdateDTO;
import org.khasanof.post_service.entity.post_block.PostBlockEntity;
import org.khasanof.post_service.enums.blocked_for.BlockedForEnum;
import org.khasanof.post_service.mapper.post_block.PostBlockMapper;
import org.khasanof.post_service.repository.post_block.PostBlockRepository;
import org.khasanof.post_service.service.AbstractService;
import org.khasanof.post_service.service.post.PostService;
import org.khasanof.post_service.validator.post_block.PostBlockValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.Instant;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Service
public class PostBlockServiceImpl extends AbstractService<PostBlockRepository, PostBlockMapper, PostBlockValidator> implements PostBlockService {

    private final PostService postService;

    public PostBlockServiceImpl(PostBlockRepository repository, PostBlockMapper mapper, PostBlockValidator validator, PostService postService) {
        super(repository, mapper, validator);
        this.postService = postService;
    }

    @Override
    public void create(PostBlockCreateDTO dto) {
        validator.validCreateDTO(dto);
        if (postService.existByIdAndCheckBlocked(dto.getBlockedPostId()))
            throw new NotFoundException("Post not found");
        if (!BlockedForEnum.hasBlockedFor(dto.getBlockedCode()))
            throw new RuntimeException("Blocked Code Invalid!");
        PostBlockEntity postBlockEntity = mapper.toCreateDTO(dto);
        postBlockEntity.setDuration(returnBlockedTime(dto.getBlockedCode()));
        repository.save(postBlockEntity);
    }

    @Override
    public void update(PostBlockUpdateDTO dto) {
        validator.validUpdateDTO(dto);
        PostBlockEntity entity = repository.findById(dto.getId()).orElseThrow(() -> {
            throw new NotFoundException("Post block not found");
        });
        if (!BlockedForEnum.hasBlockedFor(dto.getBlockedCode()))
            throw new RuntimeException("Blocked Code Invalid!");
        BeanUtils.copyProperties(dto, entity);
        entity.setUpdatedAt(Instant.now());
        entity.setCreatedBy("-1");
        entity.setDuration(returnBlockedTime(dto.getBlockedCode()));
        repository.save(entity);
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id)) {
            throw new NotFoundException("Post Block not found");
        }
        repository.deleteById(id);
    }

    @Override
    public PostBlockGetDTO get(String id) {
        validator.validKey(id);
        return mapToGetDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post Block not found");
                        })
        );
    }

    @Override
    public PostBlockDetailDTO detail(String id) {
        validator.validKey(id);
        return mapper.fromDetailDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post Block not found");
                        })
        );
    }

    @Override
    public List<PostBlockGetDTO> list(PostBlockCriteria criteria) {
        return repository.findAll(
                        PageRequest.of(
                                criteria.getPage(),
                                criteria.getSize()
                        )).stream()
                .map(this::mapToGetDTO)
                .toList();
    }

    private PostBlockGetDTO mapToGetDTO(PostBlockEntity entity) {
        PostBlockGetDTO postBlockGetDTO = mapper.fromGetDTO(entity);
        postBlockGetDTO.setBlockedPostId(entity.getPostId().getId());
        return postBlockGetDTO;
    }

    private Instant returnBlockedTime(String blockedCode) {
        return switch (BlockedForEnum.valueOf(blockedCode.toUpperCase(Locale.ROOT))) {
            case REPORT -> Instant.now().plusNanos(TimeUnit.HOURS.toNanos(48));
            case BAD_CONTENT -> Instant.now().plusNanos(TimeUnit.HOURS.toNanos(92));
            case DONT_USE_WORDS -> Instant.now().plusNanos(TimeUnit.HOURS.toNanos(24));
        };
    }
}
