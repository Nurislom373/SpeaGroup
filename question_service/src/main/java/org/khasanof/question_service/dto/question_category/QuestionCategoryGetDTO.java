package org.khasanof.question_service.dto.question_category;

import lombok.*;
import org.khasanof.question_service.dto.GenericDTO;

import java.util.List;

/**
 * @author Nurislom
 * Date: 12/7/2022
 * Time: 9:59 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class QuestionCategoryGetDTO extends GenericDTO {
    private String questionIdVal;
    private List<String> categories;
}
