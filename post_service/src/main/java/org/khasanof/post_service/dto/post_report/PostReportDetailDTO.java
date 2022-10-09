package org.khasanof.post_service.dto.post_report;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.entity.report.ReportEntity;

import java.time.Instant;
import java.util.LinkedList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostReportDetailDTO extends GenericDTO {
    private PostEntity post;
    private LinkedList<ReportEntity> reports;
    private Integer countReports;
    private Integer totalPointReports;
    private Instant lastReportTime;
}
