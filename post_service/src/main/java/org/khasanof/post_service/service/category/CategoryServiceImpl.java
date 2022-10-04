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
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class CategoryServiceImpl extends AbstractService<CategoryRepository, CategoryMapper, CategoryValidator> implements CategoryService {

    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper, CategoryValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public void create(CategoryCreateDTO dto) {
        validator.validCreateDTO(dto);
        if (repository.existsByCode(dto.getCode())) {
            throw new RuntimeException("Code is already created");
        }
        repository.save(mapper.toCreateDTO(dto));
    }

    @Override
    public void update(CategoryUpdateDTO dto) {
        validator.validUpdateDTO(dto);
        CategoryEntity entity = repository.findById(dto.getId()).orElseThrow(() -> {
            throw new NotFoundException("Category not found");
        });
        BeanUtils.copyProperties(dto, entity, "id");
        repository.save(entity);
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
        return null;
    }

    @Override
    public CategoryDetailDTO detail(String id) {
        return null;
    }

    @Override
    public List<CategoryGetDTO> list(CategoryCriteria criteria) {
        return null;
    }
}
