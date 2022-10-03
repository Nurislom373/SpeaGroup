package org.khasanof.post_service.dto.post_report;

import lombok.*;
import org.khasanof.post_service.dto.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostReportCreateDTO implements BaseDTO {
    private String reportPostId;
    private String userId;
    private String reportCode;
    private String message;
}
