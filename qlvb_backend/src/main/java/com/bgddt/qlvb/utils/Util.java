package com.bgddt.qlvb.utils;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static String NVL(String... texts) {
        for(String text: texts) {
            if(texts != null && texts.length > 0) return text;
        }
        return null;
    }
    public static String nullToEmpty(String text) {
        return text == null ? "" : text;
    }
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.length() == 0;
    }

    public static String NVL(String value1, String value2, String value3) {
        return NVL(value1, NVL(value2, value3));
    }

    public static String NVL(String value1, String value2) {
        return StringUtils.isBlank(value1) ? value2 : value1;
    }

    public static String formatDate(Timestamp date) {
        return formatDate(date, "dd/MM/yyyy hh:mm:ss");
    }

    public static String formatDate(Timestamp date, String format) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            return formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String formatDate(Date date) {
        return formatDate(date, "dd/MM/yyyy hh:mm:ss");
    }

    public static String formatDate(Date date, String format) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            return formatter.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * convert String to Date
     *
     * @param date
     * @param format default dd/MM/yyyy HH:mm:ss
     * @return
     */
    public static Date stringToDate(String date, String format) {
        try {
            Date newDate = new SimpleDateFormat(format).parse(date);
            return newDate;
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date stringToDate(String date) {
        return stringToDate(date, "dd/MM/yyyy HH:mm:ss");
    }
}
