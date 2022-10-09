package org.khasanof.post_service.entity.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.post_service.enums.reports.ReportsEnum;

import java.time.Instant;

@Getter
@Setter
public class ReportEntity {
    private String userId;
    private String reportCode;
    private String message;
    private Instant sendTime;
    private Integer point;

    public ReportEntity(String userId, String reportCode, String message) {
        this.userId = userId;
        this.reportCode = reportCode;
        this.message = message;
        this.sendTime = Instant.now();
        setPoint(reportCode);
    }

    private void setPoint(String reportCode) {
        if (!ReportsEnum.hasReport(reportCode)) {
            throw new RuntimeException("Invalid Report Code!");
        }
        this.point = ReportsEnum.getReportPoint(reportCode);
    }
}
