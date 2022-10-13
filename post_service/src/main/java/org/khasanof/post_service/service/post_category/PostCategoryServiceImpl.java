package org.khasanof.post_service.service.post_category;

import org.khasanof.post_service.criteria.post_category.PostCategoryCriteria;
import org.khasanof.post_service.dto.post_category.PostCategoryAddAllDTO;
import org.khasanof.post_service.dto.post_category.PostCategoryAddDTO;
import org.khasanof.post_service.dto.post_category.PostCategoryDetailDTO;
import org.khasanof.post_service.dto.post_category.PostCategoryGetDTO;
import org.khasanof.post_service.entity.category.CategoryEntity;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.entity.post_category.PostCategoryEntity;
import org.khasanof.post_service.mapper.post.PostMapper;
import org.khasanof.post_service.mapper.post_category.PostCategoryMapper;
import org.khasanof.post_service.repository.post_category.PostCategoryRepository;
import org.khasanof.post_service.service.AbstractService;
import org.khasanof.post_service.service.category.CategoryService;
import org.khasanof.post_service.service.post.PostService;
import org.khasanof.post_service.validator.post_category.PostCategoryValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostCategoryServiceImpl extends AbstractService<PostCategoryRepository, PostCategoryMapper, PostCategoryValidator> implements PostCategoryService {

    private final PostService postService;
    private final PostMapper postMapper;
    private final CategoryService categoryService;
    private final MongoTemplate mongoTemplate;

    public PostCategoryServiceImpl(PostCategoryRepository repository, PostCategoryMapper mapper, PostCategoryValidator validator, PostService postService, PostMapper postMapper, CategoryService categoryService, MongoTemplate mongoTemplate) {
        super(repository, mapper, validator);
        this.postService = postService;
        this.postMapper = postMapper;
        this.categoryService = categoryService;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void addCategory(PostCategoryAddDTO dto) {
        validator.validCreateDTO(dto);
        Optional<PostCategoryEntity> optional = repository.findByPostIdQuery(dto.getCategoryPostId());
        if (optional.isPresent()) {
            PostCategoryEntity postCategoryEntity = optional.get();
            List<CategoryEntity> categories = postCategoryEntity.getCategories();
            categories.add(categoryService.getEntity(dto.getCategoryId()));
            postCategoryEntity.setCategories(categories);
            postCategoryEntity.setUpdatedAt(Instant.now());
            repository.save(postCategoryEntity);
        } else {
            if (postService.existByIdAndCheckBlocked(dto.getCategoryPostId())) {
                throw new RuntimeException("Post is blocked!");
            }
            PostEntity postEntity = postMapper.toGetDTO(postService.get(dto.getCategoryPostId()));
            PostCategoryEntity postCategoryEntity = mapper.toCreateDTO(dto);
            postCategoryEntity.setPostId(postEntity);
            List<CategoryEntity> categories = postCategoryEntity.getCategories();
            categories.add(categoryService.getEntity(dto.getCategoryId()));
            postCategoryEntity.setCategories(categories);
            repository.save(postCategoryEntity);
        }
    }

    @Override
    public void addAllCategory(PostCategoryAddAllDTO dto) {
        validator.validAddAllDTO(dto);
        String categoryPostId = dto.getCategoryPostId();
        System.out.println("categoryPostId = " + categoryPostId);
        PostCategoryEntity categoryEntity = mongoTemplate.findOne(
                Query.query(new Criteria("postId")
                        .is(postService.getEntity(dto.getCategoryPostId()))
                ), PostCategoryEntity.class);
        System.out.println("categoryEntity = " + categoryEntity);
        if (!Objects.isNull(categoryEntity)) {
            List<CategoryEntity> categories = categoryEntity.getCategories();
            List<String> categoryIds = dto.getCategoryIds();
            categoryIds.forEach((obj) -> {
                String haveID = categories.stream()
                        .map(CategoryEntity::getId)
                        .filter(id -> id.equals(obj))
                        .findFirst().orElseThrow();
                // TODO writing true logic with stream api
                categoryIds.removeIf(f -> f.equals(haveID));
            });
            if (categoryIds.size() != 0) {
                categories.addAll(categoryService.getAllEntity(categoryIds));
                categoryEntity.setCategories(categories);
                categoryEntity.setUpdatedAt(Instant.now());
                repository.save(categoryEntity);
            }
        } else {
            if (postService.existByIdAndCheckBlocked(dto.getCategoryPostId())) {
                throw new RuntimeException("Post is blocked!");
            }
            PostEntity postEntity = postService.getEntity(dto.getCategoryPostId());
            System.out.println("postEntity = " + postEntity);
            PostCategoryEntity postCategoryEntity = new PostCategoryEntity();
            postCategoryEntity.setPostId(postEntity);
            List<CategoryEntity> categories = new ArrayList<>(categoryService.getAllEntity(dto.getCategoryIds()));
            postCategoryEntity.setCategories(categories);
            repository.save(postCategoryEntity);
        }
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id)) {
            throw new NotFoundException("Post Category not found");
        }
        repository.deleteById(id);
    }

    @Override
    public void deleteCategory(String id, String categoryId) {
        validator.validKey(id);
        validator.validKey(categoryId);
        PostCategoryEntity postCategory = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Post Category not found");
        });
        List<CategoryEntity> categories = postCategory.getCategories();
        if (!categories.removeIf(f -> f.getId().equals(categoryId))) {
            throw new NotFoundException("Category not found");
        }
        postCategory.setCategories(categories);
        repository.save(postCategory);
    }

    @Override
    public PostCategoryGetDTO get(String id) {
        validator.validKey(id);
        return returnToGetDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post Category not found");
                        })
        );
    }

    @Override
    public PostCategoryDetailDTO detail(String id) {
        validator.validKey(id);
        return mapper.fromDetailDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Post Category not found");
                        })
        );
    }

    @Override
    public List<PostCategoryGetDTO> list(PostCategoryCriteria criteria) {
        return repository.findAll(
                        PageRequest.of(
                                criteria.getPage(), criteria.getSize()
                        )
                ).stream()
                .map(this::returnToGetDTO)
                .toList();
    }

    private PostCategoryGetDTO returnToGetDTO(PostCategoryEntity entity) {
        PostCategoryGetDTO postCategoryGetDTO = mapper.fromGetDTO(entity);
        postCategoryGetDTO.setCategoryPostId(entity.getPostId().getId());
        postCategoryGetDTO.setCategoryId(
                entity.getCategories()
                        .stream()
                        .map(CategoryEntity::getId)
                        .toList());
        return postCategoryGetDTO;
    }
}
