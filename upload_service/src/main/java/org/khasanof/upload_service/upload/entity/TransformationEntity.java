package org.khasanof.upload_service.upload.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/15/2022
 * <br/>
 * Time: 9:10 PM
 * <br/>
 * Package: org.khasanof.upload_service.upload.entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransformationEntity {
    private String transformation;
    private Integer width;
    private Integer height;
    private String url;
    private String secure_url;
}
