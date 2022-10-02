package org.khasanof.post_service.entity.post_report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khasanof.post_service.entity.Auditable;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.entity.report.ReportEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.LinkedList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "post_report")
public class PostReportEntity extends Auditable {
    @DocumentReference
    @Field(name = "post_id")
    private PostEntity postId;
    private LinkedList<ReportEntity> reports;
    @Field(name = "count_reports")
    private Integer countReports;
    @Field(name = "total_point_reports")
    private Long totalPointReports;
    @Field(name = "last_report_time")
    private Instant lastReportTime;
}
