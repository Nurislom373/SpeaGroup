package org.khasanof.question_service.entity.report;

import lombok.Getter;
import lombok.Setter;
import org.khasanof.question_service.enums.report.ReportsEnum;

import java.time.Instant;

@Getter
@Setter
public class ReportEntity {
    private String userId;
    private ReportsEnum reportCode;
    private String message;
    private Instant sendTime;

    public ReportEntity(String userId, ReportsEnum reportCode, String message) {
        this.userId = userId;
        this.reportCode = reportCode;
        this.message = message;
        this.sendTime = Instant.now();
    }
}
