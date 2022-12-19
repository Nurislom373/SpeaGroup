package org.khasanof.upload_service.upload.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "uploads")
@Builder
@ToString
public class CloudinaryEntity extends Auditable {
    private String asset_id;
    private String public_id;
    private Integer version;
    private String version_id;
    private String api_key;
    private String signature;
    private Integer width;
    private Integer height;
    private String format;
    private Boolean overwritten;
    private String original_extension;
    private String resource_type;
    private String tags;
    private String folder;
    private Integer pages;
    private Integer bytes;
    private String type;
    private String etag;
    private boolean placeholder;
    private String url;
    private String secure_url;
    private String access_mode;
    private String original_filename;
//    private TransformationEntity[] eager;
}
