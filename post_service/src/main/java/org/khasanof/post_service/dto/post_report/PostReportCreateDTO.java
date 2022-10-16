package org.khasanof.post_service.dto.post_report;

import lombok.*;
import org.khasanof.post_service.dto.BaseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostReportCreateDTO implements BaseDTO {
    @NotBlank
    @Size(min = 24, max = 24)
    private String reportPostId;
    @NotBlank
    @Size(min = 24, max = 24)
    private String userId;
    @NotBlank
    @Size(min = 3, max = 120)
    private String reportCode;
    @NotBlank
    @Size(min = 10, max = 2500)
    private String message;
}
