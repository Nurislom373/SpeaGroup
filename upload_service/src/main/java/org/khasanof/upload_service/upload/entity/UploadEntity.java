package org.khasanof.upload_service.upload.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "uploads")
@Builder
public class UploadEntity extends Auditable {
    private String name;
    private String originalName;
    private String url;
    private Integer size;
    private Integer width;
    private Integer height;
}
