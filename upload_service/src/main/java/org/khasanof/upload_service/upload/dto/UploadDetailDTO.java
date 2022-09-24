package org.khasanof.upload_service.upload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadDetailDTO extends GenericDTO {
    private String name;
    private String originalName;
    private String url;
    private Integer size;
    private Integer width;
    private Integer height;
}
