package com.example.backendjavaapijob.infrastructure.utils;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
    // ISO 8601 BASIC is used by the API signature
    public static String ISO_8601BASIC_DATE_PATTERN = "yyyyMMdd'T'HHmmss'Z'";

    public static boolean isIsoTimestamp(String s) {

        return s.matches("\\d{8}T\\d{6}Z");
    }
    public static Date parseIsoDateTime(String s)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(ISO_8601BASIC_DATE_PATTERN);
        dateFormat.setLenient(false);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date result = dateFormat.parse(s, new ParsePosition(0));
        return result;
    }
    public static String parseDateToIso(Date date)
    {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        return df.format(new Date());
    }
}
