package org.khasanof.post_service.mapper.post_report;

import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.dto.post_report.PostReportCreateDTO;
import org.khasanof.post_service.dto.post_report.PostReportDetailDTO;
import org.khasanof.post_service.dto.post_report.PostReportGetDTO;
import org.khasanof.post_service.entity.post_report.PostReportEntity;
import org.khasanof.post_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PostReportMapper extends GenericMapper<PostReportCreateDTO, GenericDTO, PostReportGetDTO, PostReportDetailDTO, PostReportEntity> {
}
