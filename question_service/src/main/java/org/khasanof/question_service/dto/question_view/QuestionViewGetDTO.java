package org.khasanof.question_service.dto.question_view;

import lombok.*;
import org.khasanof.question_service.dto.GenericDTO;

import java.time.Instant;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/26/2022
 * <br/>
 * Time: 8:31 PM
 * <br/>
 * Package: org.khasanof.question_service.dto.question_view
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class QuestionViewGetDTO extends GenericDTO {
    private String questionStrId;

    private Integer viewsCount;

    private Instant createdAt;
}
