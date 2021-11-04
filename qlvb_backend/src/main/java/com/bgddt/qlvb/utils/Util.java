package com.bgddt.qlvb.utils;

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
}
