package org.khasanof.question_service.dto.question;

import lombok.*;
import org.khasanof.question_service.dto.GenericDTO;

import java.time.Instant;
import java.util.List;

/**
 * @author Nurislom
 * Date: 12/6/2022
 * Time: 7:18 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionGetDTO extends GenericDTO {
    private String username;
    private String title;
    private String imagePath;
    private boolean isSolving;
    private Integer answerCount;
    private Integer viewCount;
    private List<String> categoryNames;
    private Instant createdAt;
}
