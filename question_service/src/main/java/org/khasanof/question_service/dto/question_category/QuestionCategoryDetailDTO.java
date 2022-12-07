package org.khasanof.question_service.dto.question_category;

import lombok.*;
import org.khasanof.question_service.dto.GenericDTO;
import org.khasanof.question_service.dto.category.CategoryDetailDTO;
import org.khasanof.question_service.entity.question.QuestionEntity;

import java.time.Instant;
import java.util.List;

/**
 * @author Nurislom
 * Date: 12/7/2022
 * Time: 10:00 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class QuestionCategoryDetailDTO extends GenericDTO {
    private QuestionEntity question;
    private List<CategoryDetailDTO> categoryNames;
    private Instant createdAt;
    private Instant updatedAt;
}
