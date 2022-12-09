package org.khasanof.question_service.dto.question_category;

import lombok.*;
import org.khasanof.question_service.dto.GenericDTO;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Nurislom
 * Date: 12/7/2022
 * Time: 9:57 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class QuestionCategoryAddDTO extends GenericDTO {

    @NotNull
    private List<String> categories;

    public QuestionCategoryAddDTO(String id, List<String> categories) {
        super(id);
        this.categories = categories;
    }
}
