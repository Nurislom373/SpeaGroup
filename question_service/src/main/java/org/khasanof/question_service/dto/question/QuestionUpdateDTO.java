package org.khasanof.question_service.dto.question;

import lombok.*;
import org.khasanof.question_service.dto.GenericDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Nurislom
 * Date: 12/6/2022
 * Time: 7:11 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class QuestionUpdateDTO extends GenericDTO {
    @NotBlank
    @Size(min = 20, max = 2500)
    private String title;
    @NotBlank
    private String imagePath;

    public QuestionUpdateDTO(String id, String title, String imagePath) {
        super(id);
        this.title = title;
        this.imagePath = imagePath;
    }
}
