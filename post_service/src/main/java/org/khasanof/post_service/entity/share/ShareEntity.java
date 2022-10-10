package org.khasanof.post_service.entity.share;

import lombok.*;
import org.khasanof.post_service.enums.share.ShareType;

import java.time.Instant;

@Getter
@Setter
@ToString
public class ShareEntity {
    private String userId;
    private String type;
    private Instant sendTime;

    public ShareEntity(String userId, String type) {
        this.userId = userId;
        setType(type);
    }

    private void setType(String type) {
        if (!ShareType.hasReport(type))
            throw new RuntimeException("Invalid Type!");
        this.type = type;
    }
}
