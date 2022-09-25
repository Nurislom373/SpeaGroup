package org.khasanof.upload_service.upload.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "uploads")
@Builder
public class CouldinaryEntity extends Auditable {
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
    private List<String> tags;
    private String folder;
    private String original_filename;
    private String api_key;
    private String bytes;
    private Boolean overwritten;
    private String width;
    private String etag;
    private Boolean placeholder;
    private String height;
}
