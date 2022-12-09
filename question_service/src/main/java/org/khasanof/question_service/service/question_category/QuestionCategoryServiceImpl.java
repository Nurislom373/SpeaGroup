package org.khasanof.question_service.service.question_category;

import org.khasanof.question_service.criteria.question_category.QuestionCategoryCriteria;
import org.khasanof.question_service.dto.category.CategoryDetailDTO;
import org.khasanof.question_service.dto.category.CategoryFindAllRequestDTO;
import org.khasanof.question_service.dto.question_category.*;
import org.khasanof.question_service.entity.question.QuestionEntity;
import org.khasanof.question_service.entity.question_category.QuestionCategoryEntity;
import org.khasanof.question_service.exception.exceptions.AlreadyCreatedException;
import org.khasanof.question_service.exception.exceptions.NotFoundException;
import org.khasanof.question_service.mapper.question_category.QuestionCategoryMapper;
import org.khasanof.question_service.repository.question_category.QuestionCategoryRepository;
import org.khasanof.question_service.service.AbstractService;
import org.khasanof.question_service.service.question.QuestionService;
import org.khasanof.question_service.validator.question_category.QuestionCategoryValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/7/2022
 * <br/>
 * Time: 10:14 PM
 * <br/>
 * Package: org.khasanof.question_service.service.question_category
 */
@Service
public class QuestionCategoryServiceImpl extends AbstractService<QuestionCategoryRepository, QuestionCategoryMapper, QuestionCategoryValidator>
        implements QuestionCategoryService {

    private final QuestionService questionService;
    private final CategoryFeignClient feignClient;

    public QuestionCategoryServiceImpl(QuestionCategoryRepository repository, QuestionCategoryMapper mapper, QuestionCategoryValidator validator, QuestionService questionService, CategoryFeignClient feignClient) {
        super(repository, mapper, validator);
        this.questionService = questionService;
        this.feignClient = feignClient;
    }

    @Override
    public void create(QuestionCategoryCreateDTO dto) {
        validator.validCreateDTO(dto);
        QuestionEntity entity = questionService.findById(dto.getQuestionIdVal());
        if (repository.existsByQuestionId(entity)) {
            throw new AlreadyCreatedException("Question Already Created!");
        }
        QuestionCategoryEntity questionCategoryEntity = mapper.toCreateDTO(dto);
        questionCategoryEntity.setQuestionId(entity);
        repository.save(questionCategoryEntity);
    }

    @Override
    public void addCategory(QuestionCategoryAddDTO dto) {
        validator.validUpdateDTO(dto);
        QuestionCategoryEntity entity = findById(dto.getId());
        List<String> categories = new ArrayList<>(new HashSet<>(dto.getCategories()));
        List<String> list = entity.getCategories();
        List<String> remove = new ArrayList<>();
        categories.forEach((obj) -> {
            boolean anyMatch = list.stream()
                    .anyMatch(any -> any.equals(obj));
            if (anyMatch) {
                remove.add(obj);
            }
        });
        categories.removeAll(remove);
        if (!categories.isEmpty()) {
            list.addAll(dto.getCategories());
            entity.setCategories(list);
            entity.setUpdatedAt(Instant.now());
            entity.setUpdatedBy(entity.getQuestionId().getUserId());
            repository.save(entity);
        }
    }

    @Override
    public void deleteCategory(QuestionCategoryDeleteDTO dto) {
        validator.validDeleteDTO(dto);
        QuestionCategoryEntity category = findById(dto.getId());
        List<String> removeDuplicates = new ArrayList<>(new HashSet<>(dto.getCategories()));
        List<String> categories = category.getCategories();
        categories.removeAll(removeDuplicates);
        category.setCategories(categories);
        category.setUpdatedAt(Instant.now());
        category.setUpdatedBy(category.getQuestionId().getUserId());
        repository.save(category);
    }

    private QuestionCategoryEntity findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Question Category not found");
                });
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id)) {
            throw new NotFoundException("Question not found");
        }
        repository.deleteById(id);
    }

    @Override
    public QuestionCategoryGetDTO get(String id) {
        validator.validKey(id);
        return returnGetDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Question not found");
                        }));
    }

    @Override
    public QuestionCategoryDetailDTO detail(String id) {
        validator.validKey(id);
        try {
            return returnDetailDTO(
                    repository.findById(id)
                            .orElseThrow(() -> {
                                throw new NotFoundException("Question not found");
                            }));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<QuestionCategoryGetDTO> list(QuestionCategoryCriteria criteria) {
        return repository.findAll(PageRequest.of(criteria.getPage(),
                        criteria.getSize()))
                .stream().map(this::returnGetDTO).toList();
    }

    private QuestionCategoryGetDTO returnGetDTO(QuestionCategoryEntity entity) {
        QuestionCategoryGetDTO dto = mapper.fromGetDTO(entity);
        dto.setQuestionIdVal(entity.getQuestionId().getId());
        return dto;
    }

    private QuestionCategoryDetailDTO returnDetailDTO(QuestionCategoryEntity entity) throws Exception {
        QuestionCategoryDetailDTO dto = mapper.fromDetailDTO(entity);
        dto.setQuestion(entity.getQuestionId());
        List<CategoryDetailDTO> list = feignClient.findAllById(
                        new CategoryFindAllRequestDTO(entity.getCategories())).getData();
        dto.setCategoriesDetails(list);
        return dto;
    }
}
