package com.example.termtracker.Helper;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Utility {

    public static final String DATE_FORMATTER = "yyyy-MM-dd";
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(Utility.DATE_FORMATTER, Locale.US);

    public static String localDateToString(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static LocalDate stringToLocalDate(String str) {
        return LocalDate.parse(str, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static long getEpochSecondsUTC() {
        return ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC")).toEpochSecond();
    }

}
