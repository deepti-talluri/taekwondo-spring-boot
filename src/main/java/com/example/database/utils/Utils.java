package com.example.database.utils;

public class Utils {
    private Utils() {

    }

    public static String trimToNull(String string) {
        if (string == null || string.trim().length() == 0)
            return null;
        return string.trim();
    }
}
