package org.khasanof.post_service.service.category;

import org.khasanof.post_service.criteria.category.CategoryCriteria;
import org.khasanof.post_service.dto.category.CategoryCreateDTO;
import org.khasanof.post_service.dto.category.CategoryDetailDTO;
import org.khasanof.post_service.dto.category.CategoryGetDTO;
import org.khasanof.post_service.dto.category.CategoryUpdateDTO;
import org.khasanof.post_service.entity.category.CategoryEntity;
import org.khasanof.post_service.mapper.category.CategoryMapper;
import org.khasanof.post_service.repository.category.CategoryRepository;
import org.khasanof.post_service.service.AbstractService;
import org.khasanof.post_service.validator.category.CategoryValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl extends AbstractService<CategoryRepository, CategoryMapper, CategoryValidator> implements CategoryService {

    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper, CategoryValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public void create(CategoryCreateDTO dto) {
        validator.validCreateDTO(dto);
        if (repository.existsByCode(dto.getCode()))
            throw new RuntimeException("Already Created Code!");
        repository.save(mapper.toCreateDTO(dto));
    }

    @Override
    public void update(CategoryUpdateDTO dto) {
        validator.validUpdateDTO(dto);
        if (repository.existsByCode(dto.getCode()))
            throw new RuntimeException("Already Created Code!");
        CategoryEntity category = repository.findById(dto.getId())
                .orElseThrow(() -> {
                    throw new NotFoundException("Category not found");
                });
        BeanUtils.copyProperties(dto, category);
        repository.save(category);
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException("Category not found");
        }
    }

    @Override
    public CategoryGetDTO get(String id) {
        validator.validKey(id);
        return mapper.fromGetDTO(
                repository.findById(id).orElseThrow(() -> {
                    throw new NotFoundException("Category not found");
                }));
    }

    @Override
    public CategoryDetailDTO detail(String id) {
        validator.validKey(id);
        return mapper.fromDetailDTO(
                repository.findById(id).orElseThrow(() -> {
                    throw new NotFoundException("Category not found");
                }));
    }

    @Override
    public List<CategoryGetDTO> list(CategoryCriteria criteria) {
        return mapper.fromGetListDTO(
                repository.findAll(
                        PageRequest.of(
                                criteria.getPage(),
                                criteria.getSize(),
                                criteria.getDirection(),
                                criteria.getFieldsEnum().getValue()
                        )
                ).toList()
        );
    }

    @Override
    public CategoryEntity getEntity(String id) {
        validator.validKey(id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Category not found");
                });
    }

    @Override
    public List<CategoryEntity> getAllEntity(List<String> ids) {
        Assert.notNull(ids, "List must be not null!");
        return ids.stream()
                        .map(repository::findById)
                        .map(Optional::orElseThrow)
                        .toList();
    }

    @Override
    public List<CategoryDetailDTO> findAllById(List<String> ids) {
        validator.validKeys(ids);
        return ids.stream()
                .map(repository::findById)
                .map(Optional::orElseThrow)
                .map(mapper::fromDetailDTO)
                .toList();
    }

    @Override
    public long count() {
        return repository.count();
    }
}
