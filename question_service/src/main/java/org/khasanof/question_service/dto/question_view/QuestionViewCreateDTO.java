package org.khasanof.question_service.dto.question_view;

import lombok.*;
import org.khasanof.question_service.annotation.MongoIdConstraint;
import org.khasanof.question_service.dto.BaseDTO;

import javax.validation.constraints.NotBlank;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/26/2022
 * <br/>
 * Time: 8:29 PM
 * <br/>
 * Package: org.khasanof.question_service.dto.question_view
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionViewCreateDTO implements BaseDTO {

    @NotBlank
    @MongoIdConstraint
    private String questionStrId;

    @NotBlank
    @MongoIdConstraint
    private String userId;
}
