package org.khasanof.post_service.validator.post_report;

import org.bson.types.ObjectId;
import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.dto.post_report.PostReportCreateDTO;
import org.khasanof.post_service.enums.reports.ReportsEnum;
import org.khasanof.post_service.exceptions.exceptions.InvalidValidationException;
import org.khasanof.post_service.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PostReportValidator extends AbstractValidator<PostReportCreateDTO, GenericDTO, String> {

    @Override
    public void validCreateDTO(PostReportCreateDTO postReportCreateDTO) throws InvalidValidationException {
        if (Objects.isNull(postReportCreateDTO)) {
            throw new InvalidValidationException("DTO is null");
        }
        if (!ObjectId.isValid(postReportCreateDTO.getReportPostId()) || !ObjectId.isValid(postReportCreateDTO.getUserId())) {
            throw new InvalidValidationException("Invalid ID!");
        }
        if (!ReportsEnum.hasReport(postReportCreateDTO.getReportCode())) {
            throw new InvalidValidationException("Invalid ReportCode!");
        }
    }

    @Override
    public void validUpdateDTO(GenericDTO genericDTO) throws InvalidValidationException {

    }

    @Override
    public void validKey(String s) throws InvalidValidationException {
        if (Objects.isNull(s)) {
            throw new InvalidValidationException("ID is null");
        }
        if (!ObjectId.isValid(s)) {
            throw new InvalidValidationException("Invalid ID!");
        }
    }
}
