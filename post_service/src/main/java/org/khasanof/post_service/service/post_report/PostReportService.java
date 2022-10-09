package org.khasanof.post_service.service.post_report;

import org.khasanof.post_service.criteria.post_report.PostReportCriteria;
import org.khasanof.post_service.dto.post_report.PostReportCreateDTO;
import org.khasanof.post_service.dto.post_report.PostReportDetailDTO;
import org.khasanof.post_service.dto.post_report.PostReportGetDTO;
import org.khasanof.post_service.service.BaseService;
import org.khasanof.post_service.service.GenericGDLService;

public interface PostReportService extends GenericGDLService<PostReportGetDTO, PostReportDetailDTO, String, PostReportCriteria>, BaseService {

    void create(PostReportCreateDTO dto);

    void delete(String id);

}
