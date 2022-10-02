package org.khasanof.post_service.entity.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportEntity {
    private String userId;
    private String reportCode;
    private String message;
    private Instant sendTime;
    private Integer point;
}
