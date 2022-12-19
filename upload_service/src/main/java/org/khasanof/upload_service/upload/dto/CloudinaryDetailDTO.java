package org.khasanof.upload_service.upload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CloudinaryDetailDTO extends GenericDTO {
    private String signature;
    private String format;
    private String resource_type;
    private String secure_url;
    private String asset_id;
    private String version_id;
    private String type;
    private String version;
    private String url;
    private String public_id;
    private String tags;
    private String folder;
    private String original_filename;
    private String api_key;
    private Integer bytes;
    private Boolean overwritten;
    private String width;
    private String etag;
    private Boolean placeholder;
    private String height;
    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
}
