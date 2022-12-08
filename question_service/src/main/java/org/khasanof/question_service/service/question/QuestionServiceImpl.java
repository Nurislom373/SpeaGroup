package org.khasanof.question_service.service.question;

import org.khasanof.question_service.criteria.question.QuestionCriteria;
import org.khasanof.question_service.dto.auth_user.AuthUserGetDTO;
import org.khasanof.question_service.dto.question.QuestionCreateDTO;
import org.khasanof.question_service.dto.question.QuestionDetailDTO;
import org.khasanof.question_service.dto.question.QuestionGetDTO;
import org.khasanof.question_service.dto.question.QuestionUpdateDTO;
import org.khasanof.question_service.entity.question.QuestionEntity;
import org.khasanof.question_service.exception.exceptions.ClientResponseException;
import org.khasanof.question_service.exception.exceptions.NotFoundException;
import org.khasanof.question_service.mapper.question.QuestionMapper;
import org.khasanof.question_service.repository.question.QuestionRepository;
import org.khasanof.question_service.service.AbstractService;
import org.khasanof.question_service.validator.question.QuestionValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/6/2022
 * <br/>
 * Time: 7:40 PM
 * <br/>
 * Package: org.khasanof.question_service.service.question
 */
@Service
public class QuestionServiceImpl extends AbstractService<QuestionRepository, QuestionMapper, QuestionValidator>
        implements QuestionService {

    private final AuthUserFeignClient feignClient;

    public QuestionServiceImpl(QuestionRepository repository, QuestionMapper mapper, QuestionValidator validator, AuthUserFeignClient feignClient) {
        super(repository, mapper, validator);
        this.feignClient = feignClient;
    }

    @Override
    public void create(QuestionCreateDTO dto) {
        validator.validCreateDTO(dto);
        repository.save(mapper.toCreateDTO(dto));
    }

    @Override
    public void update(QuestionUpdateDTO dto) {
        validator.validUpdateDTO(dto);
        QuestionEntity entity = repository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException("Question not found"));
        BeanUtils.copyProperties(dto, entity);
        entity.setUpdatedAt(Instant.now());
        entity.setUpdatedBy(entity.getUserId());
        repository.save(entity);
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
    public QuestionGetDTO get(String id) {
        validator.validKey(id);
        return returnGetDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Question not found");
                        }));
    }

    @Override
    public QuestionDetailDTO detail(String id) {
        validator.validKey(id);
        return returnDetailDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Question not found");
                        }));
    }

    @Override
    public List<QuestionGetDTO> list(QuestionCriteria criteria) {
        return repository.findAll(PageRequest.of(criteria.getPage(),
                        criteria.getSize()))
                .stream().map(this::returnGetDTO).toList();
    }

    @Override
    public boolean existById(String id) {
        validator.validKey(id);
        return repository.existsById(id);
    }

    @Override
    public QuestionEntity findById(String id) {
        validator.validKey(id);
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Question not found"));
    }

    private QuestionGetDTO returnGetDTO(QuestionEntity entity) {
        try {
            QuestionGetDTO dto = mapper.fromGetDTO(entity);
            AuthUserGetDTO data = feignClient.get(entity.getUserId()).getData();
            dto.setUsername(data.getUsername());
//            TODO will finishing get DTO
            return dto;
        } catch (Exception e) {
            throw new ClientResponseException("Client Exception!");
        }
    }

    private QuestionDetailDTO returnDetailDTO(QuestionEntity entity) {
        QuestionDetailDTO dto = mapper.fromDetailDTO(entity);
        //            TODO will finishing detail DTO
        return dto;
    }
}
