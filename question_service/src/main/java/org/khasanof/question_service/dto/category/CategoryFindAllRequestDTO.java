package org.khasanof.question_service.dto.category;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryFindAllRequestDTO {
    private List<String> ids;
}
