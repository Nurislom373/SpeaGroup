package org.khasanof.question_service.dto.question;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.*;
import org.khasanof.question_service.annotation.MongoIdConstraint;
import org.khasanof.question_service.dto.BaseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Nurislom
 * Date: 12/6/2022
 * Time: 7:08 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionCreateDTO implements BaseDTO {
    @MongoIdConstraint
    private String userId;
    @NotBlank
    @Size(min = 20, max = 2500)
    private String title;
    @NotBlank
    @Size(min = 5, max = 2500)
    private String imagePath;
}
