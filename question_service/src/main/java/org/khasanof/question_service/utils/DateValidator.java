package org.khasanof.question_service.utils;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator {
    private final DateTimeFormatter dateTimeFormatter;

    public DateValidator(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

    public boolean isValid(String var) {
        try {
            this.dateTimeFormatter.parse(var);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
