package org.khasanof.upload_service.upload.dto;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CloudinaryGetDTO extends GenericDTO {
    private String signature;
    private String format;
    private String resource_type;
    private String secure_url;
    private String url;
    private Integer bytes;
}
