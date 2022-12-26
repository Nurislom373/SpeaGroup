package org.khasanof.question_service.dto.question_view;

import lombok.*;
import org.khasanof.question_service.dto.GenericDTO;
import org.khasanof.question_service.entity.question.QuestionEntity;
import org.khasanof.question_service.entity.view.ViewEntity;

import java.time.Instant;
import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/26/2022
 * <br/>
 * Time: 8:34 PM
 * <br/>
 * Package: org.khasanof.question_service.dto.question_view
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class QuestionViewDetailDTO extends GenericDTO {
    private QuestionEntity question;
    private List<ViewEntity> views;
    private Instant createdAt;
    private Instant updatedAt;
}
