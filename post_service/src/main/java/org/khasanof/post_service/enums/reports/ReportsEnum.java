package org.khasanof.post_service.enums.reports;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.webjars.NotFoundException;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum ReportsEnum {
    OTHER("OTHER", 20, "Lorem Ipsum is simply dummy text of the printing and typesetting industry."),
    HATE_SPEECH("HATE_SPEECH", 20, "Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source."),
    COPYRIGHT("COPYRIGHT", 40, "Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance."),
    SEXUAL_EXPLOITATION("SEXUAL_EXPLOITATION", 50, "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."),
    BULLYING("BULLYING", 30, "It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged."),
    HARMFUL_INFORMATION("BULLYING", 25, "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."),
    HARMFUL_ACTIVITIES("HARMFUL_ACTIVITIES", 25, "Contrary to popular belief, Lorem Ipsum is not simply random text."),
    SALE_WEAPON_OR_DRUGS("SALE_WEAPON_OR_DRUGS", 50, "It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.");
    private final String value;
    private final Integer point;
    private final String description;

    public static boolean hasReport(String var) {
        return Arrays.stream(values())
                .anyMatch(
                        obj -> obj.value.equalsIgnoreCase(var)
                );
    }

    public static Integer getReportPoint(String var) {
        return Arrays.stream(values())
                .filter(f -> f.getValue().equalsIgnoreCase(var))
                .findFirst()
                .orElseThrow(() -> {
                    throw new NotFoundException("Report Code not found");
                }).getPoint();
    }
}
