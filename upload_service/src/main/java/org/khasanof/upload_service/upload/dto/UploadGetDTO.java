package org.khasanof.upload_service.upload.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UploadGetDTO extends GenericDTO {
    private String name;
    private String url;
    private Integer size;
}
