package org.khasanof.upload_service.upload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CloudinaryCriteria {
    private Integer size;
    private Integer page;
    private Sort.Direction direction;

    private CloudinaryFieldsEnum fieldsEnum;

    public Integer getPage() {
        if (Objects.isNull(page))
            page = 0;
        return page;
    }

    public Integer getSize() {
        if (Objects.isNull(size))
            size = 10;
        return size;
    }

    public Sort.Direction getSort() {
        if (Objects.isNull(direction)) {
            direction = Sort.Direction.ASC;
        }
        return direction;
    }
}
