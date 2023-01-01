package org.khasanof.question_service.service.question_view;

import org.khasanof.question_service.criteria.question_view.QuestionViewCriteria;
import org.khasanof.question_service.dto.question_view.QuestionViewCreateDTO;
import org.khasanof.question_service.dto.question_view.QuestionViewDetailDTO;
import org.khasanof.question_service.dto.question_view.QuestionViewGetDTO;
import org.khasanof.question_service.entity.question.QuestionEntity;
import org.khasanof.question_service.entity.question_view.QuestionViewEntity;
import org.khasanof.question_service.entity.view.ViewEntity;
import org.khasanof.question_service.exception.exceptions.NotFoundException;
import org.khasanof.question_service.mapper.question_view.QuestionViewMapper;
import org.khasanof.question_service.repository.question_view.QuestionViewRepository;
import org.khasanof.question_service.service.AbstractService;
import org.khasanof.question_service.service.question.QuestionService;
import org.khasanof.question_service.validator.question_view.QuestionViewValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/1/2023
 * <br/>
 * Time: 7:00 PM
 * <br/>
 * Package: org.khasanof.question_service.service.question_view
 */
@Service
public class QuestionViewServiceImpl extends AbstractService<QuestionViewRepository, QuestionViewMapper, QuestionViewValidator> implements QuestionViewService {

    private final QuestionService questionService;

    public QuestionViewServiceImpl(QuestionViewRepository repository, QuestionViewMapper mapper, QuestionViewValidator validator, QuestionService questionService) {
        super(repository, mapper, validator);
        this.questionService = questionService;
    }

    @Override
    public void create(QuestionViewCreateDTO dto) {
        validator.validCreateDTO(dto);
        QuestionEntity questionEntity = questionService.findById(dto.getQuestionStrId());
        Optional<QuestionViewEntity> optional = repository.findByQuestionId(questionEntity);
        if (optional.isPresent()) {
            QuestionViewEntity questionViewEntity = optional.get();
            List<ViewEntity> views = questionViewEntity.getViews();
            boolean anyMatch = views.stream()
                    .anyMatch(o -> o.getUserId().equals(dto.getUserId()));
            if (!anyMatch) {
                views.add(new ViewEntity(dto.getUserId()));
                questionViewEntity.setViews(views);
                questionViewEntity.setUpdatedAt(Instant.now());
                questionViewEntity.setUpdatedBy(dto.getUserId());
                repository.save(questionViewEntity);
            }
        } else {
            List<ViewEntity> views = new LinkedList<>();
            views.add(new ViewEntity(dto.getUserId()));
            QuestionViewEntity entity = mapper.toCreateDTO(dto);
            entity.setQuestionId(questionEntity);
            entity.setViews(views);
            repository.save(entity);
        }
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id)) {
            throw new NotFoundException("Question View not found");
        }
        repository.deleteById(id);
    }

    @Override
    public QuestionViewGetDTO get(String id) {
        validator.validKey(id);
        return returnGetDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Question View not found");
                        }));
    }

    @Override
    public QuestionViewDetailDTO detail(String id) {
        validator.validKey(id);
        return returnDetailDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Question View not found");
                        }));
    }

    @Override
    public List<QuestionViewGetDTO> list(QuestionViewCriteria criteria) {
        return repository.findAll(PageRequest.of(criteria.getPage(),
                        criteria.getSize()))
                .stream().map(this::returnGetDTO)
                .toList();
    }

    private QuestionViewGetDTO returnGetDTO(QuestionViewEntity entity) {
        QuestionViewGetDTO dto = mapper.fromGetDTO(entity);
        dto.setQuestionStrId(entity.getQuestionId().getId());
        dto.setViewsCount(entity.getViews().size());
        return dto;
    }

    private QuestionViewDetailDTO returnDetailDTO(QuestionViewEntity entity) {
        QuestionViewDetailDTO dto = mapper.fromDetailDTO(entity);
        dto.setQuestion(entity.getQuestionId());
        return dto;
    }
}
