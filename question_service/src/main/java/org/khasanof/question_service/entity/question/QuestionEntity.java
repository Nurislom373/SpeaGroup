package org.khasanof.question_service.entity.question;

import lombok.*;
import org.khasanof.question_service.entity.Auditable;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Document(collection = "question")
public class QuestionEntity extends Auditable {
    private String userId;
    private String title;
    private String imagePath;
    private Integer likeCount;
    private Integer commentCount;
    private boolean isSolving;
}
