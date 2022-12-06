package org.khasanof.question_service.dto.question;

import lombok.*;
import org.khasanof.question_service.dto.GenericDTO;
import org.khasanof.question_service.entity.answer.AnswerEntity;

import java.time.Instant;
import java.util.List;

/**
 * @author Nurislom
 * Date: 12/6/2022
 * Time: 7:23 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class QuestionDetailDTO extends GenericDTO {
    private String username;
    private String imagePath;
    private String title;
    private boolean isSolving;
    private Integer answerCount;
    private Integer viewCount;
    private List<AnswerEntity> answers;
    private List<String> categoryNames;
    private Instant createdAt;
}
