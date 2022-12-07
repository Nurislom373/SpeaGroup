package org.khasanof.question_service.dto.question_category;

import lombok.*;
import org.khasanof.question_service.annotation.MongoIdConstraint;
import org.khasanof.question_service.dto.BaseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Nurislom
 * Date: 12/7/2022
 * Time: 9:53 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class QuestionCategoryCreateDTO implements BaseDTO {
    @NotBlank
    @MongoIdConstraint
    private String questionIdVal;
    @NotNull
    private List<String> categories;
}
