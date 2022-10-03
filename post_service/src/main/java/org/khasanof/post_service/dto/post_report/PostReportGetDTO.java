package org.khasanof.post_service.dto.post_report;

import lombok.*;
import org.khasanof.post_service.dto.GenericDTO;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostReportGetDTO extends GenericDTO {
    private String reportPostId;
    private Integer countReports;
    private Long totalPointReports;
    private Instant lastReportTime;
}
