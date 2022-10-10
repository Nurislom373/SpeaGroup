package org.khasanof.post_service.entity.share;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShareEntity {
    private String userId;
    private String type;
    private Instant sendTime;
}
