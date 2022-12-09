package org.khasanof.question_service.dto.question_category;

import lombok.*;
import org.khasanof.question_service.dto.GenericDTO;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/9/2022
 * <br/>
 * Time: 6:01 PM
 * <br/>
 * Package: org.khasanof.question_service.dto.question_category
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class QuestionCategoryDeleteDTO extends GenericDTO {

    @NotNull
    private List<String> categories;

    public QuestionCategoryDeleteDTO(String id, List<String> categories) {
        super(id);
        this.categories = categories;
    }
}
