package org.khasanof.question_service.service.question_report;

import org.khasanof.question_service.criteria.question_report.QuestionReportCriteria;
import org.khasanof.question_service.dto.question_report.QuestionReportCreateDTO;
import org.khasanof.question_service.dto.question_report.QuestionReportDetailDTO;
import org.khasanof.question_service.dto.question_report.QuestionReportGetDTO;
import org.khasanof.question_service.entity.question.QuestionEntity;
import org.khasanof.question_service.entity.question_report.QuestionReportEntity;
import org.khasanof.question_service.entity.report.ReportEntity;
import org.khasanof.question_service.enums.report.ReportsEnum;
import org.khasanof.question_service.exception.exceptions.NotFoundException;
import org.khasanof.question_service.mapper.question_report.QuestionReportMapper;
import org.khasanof.question_service.repository.question_report.QuestionReportRepository;
import org.khasanof.question_service.service.AbstractService;
import org.khasanof.question_service.service.question.QuestionService;
import org.khasanof.question_service.validator.question_report.QuestionReportValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/26/2022
 * <br/>
 * Time: 6:14 PM
 * <br/>
 * Package: org.khasanof.question_service.service.question_report
 */
@Service
public class QuestionReportServiceImpl extends AbstractService<QuestionReportRepository,
        QuestionReportMapper, QuestionReportValidator> implements QuestionReportService {

    private final QuestionService questionService;

    public QuestionReportServiceImpl(QuestionReportRepository repository, QuestionReportMapper mapper, QuestionReportValidator validator, QuestionService questionService) {
        super(repository, mapper, validator);
        this.questionService = questionService;
    }

    @Override
    public void create(QuestionReportCreateDTO dto) {
        validator.validCreateDTO(dto);
        QuestionEntity questionEntity = questionService.findById(dto.getQuestionStrId());
        Optional<QuestionReportEntity> optional = repository.findByQuestionId(questionEntity);
        QuestionReportEntity questionReportEntity;

        if (optional.isPresent()) {
            questionReportEntity = optional.get();
            List<ReportEntity> reports = questionReportEntity.getReports();

            reports.add(new ReportEntity(dto.getUserId(),
                    dto.getReportCode(), dto.getMessage()));
            questionReportEntity.setReports(reports);
            questionReportEntity.setUpdatedAt(Instant.now());
            questionReportEntity.setUpdatedBy(dto.getUserId());
        } else {
            questionReportEntity = mapper.toCreateDTO(dto);
            LinkedList<ReportEntity> reports = new LinkedList<>();

            reports.add(new ReportEntity(dto.getUserId(),
                    dto.getReportCode(), dto.getMessage()));
            questionReportEntity.setReports(reports);
        }
        repository.save(questionReportEntity);
    }

    @Override
    public void delete(String id) {
        validator.validKey(id);
        if (!repository.existsById(id)) {
            throw new NotFoundException("Question Report not found!");
        }
        repository.deleteById(id);
    }

    @Override
    public boolean existById(String id) {
        validator.validKey(id);
        return repository.existsById(id);
    }

    @Override
    public QuestionReportGetDTO get(String id) {
        validator.validKey(id);
        return returnGetDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Question Report not found");
                        })
        );
    }

    @Override
    public QuestionReportDetailDTO detail(String id) {
        validator.validKey(id);
        return returnDetailDTO(
                repository.findById(id)
                        .orElseThrow(() -> {
                            throw new NotFoundException("Question Report not found");
                        })
        );
    }

    @Override
    public List<QuestionReportGetDTO> list(QuestionReportCriteria criteria) {
        return repository.findAll(PageRequest.of(criteria.getPage(),
                        criteria.getSize())).stream()
                .map(this::returnGetDTO).toList();
    }

    private QuestionReportGetDTO returnGetDTO(QuestionReportEntity entity) {
        QuestionReportGetDTO dto = mapper.fromGetDTO(entity);
        dto.setLastReportTime(entity.getUpdatedAt());
        dto.setReportsCount(entity.getReports().size());
        dto.setQuestionStrId(entity.getQuestionId().getId());
        int totalPoint = entity.getReports()
                .stream().mapToInt(o ->
                        ReportsEnum.getReportPoint(o.getReportCode()
                                .getValue())).sum();
        dto.setTotalPoint(totalPoint);
        return dto;
    }

    private QuestionReportDetailDTO returnDetailDTO(QuestionReportEntity entity) {
        QuestionReportDetailDTO dto = mapper.fromDetailDTO(entity);
        dto.setQuestion(entity.getQuestionId());
        dto.setCount(entity.getReports().size());
        int totalPoint = entity.getReports()
                .stream().mapToInt(o ->
                        ReportsEnum.getReportPoint(o.getReportCode()
                                .getValue())).sum();
        dto.setTotalPointReports(totalPoint);
        return dto;
    }
}
