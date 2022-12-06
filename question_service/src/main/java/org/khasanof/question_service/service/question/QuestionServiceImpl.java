package org.khasanof.question_service.service.question;

import org.khasanof.question_service.criteria.question.QuestionCriteria;
import org.khasanof.question_service.dto.question.QuestionCreateDTO;
import org.khasanof.question_service.dto.question.QuestionDetailDTO;
import org.khasanof.question_service.dto.question.QuestionGetDTO;
import org.khasanof.question_service.dto.question.QuestionUpdateDTO;
import org.khasanof.question_service.mapper.question.QuestionMapper;
import org.khasanof.question_service.repository.question.QuestionRepository;
import org.khasanof.question_service.service.AbstractService;
import org.khasanof.question_service.validator.question.QuestionValidator;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nurislom
 * Date: 12/6/2022
 * Time: 7:40 PM
 */
@Service
public class QuestionServiceImpl extends AbstractService<QuestionRepository, QuestionMapper, QuestionValidator> implements QuestionService {

    public QuestionServiceImpl(QuestionRepository repository, QuestionMapper mapper, QuestionValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public void create(QuestionCreateDTO dto) {

    }

    @Override
    public void update(QuestionUpdateDTO dto) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public QuestionGetDTO get(String id) {
        return null;
    }

    @Override
    public QuestionDetailDTO detail(String id) {
        return null;
    }

    @Override
    public List<QuestionGetDTO> list(QuestionCriteria criteria) {
        return null;
    }
}
