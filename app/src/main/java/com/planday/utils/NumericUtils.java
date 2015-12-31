package com.planday.utils;

public class NumericUtils {
    /**
     * Check if a string is Integer
     */
    public static boolean isNumeric(String number) {
        return number.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Check if a string is Integer
     */
    public static boolean isNumeric(CharSequence number) {
        return isNumeric(number.toString());
    }
}
