package org.khasanof.auth_service.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
