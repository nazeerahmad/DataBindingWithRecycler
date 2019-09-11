package com.example.databindingwithrecycler.utils;

import java.util.Locale;

public class BindingUtils {

    public static String convertToSuffix(long count) {
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format(Locale.US,"%.1f%c",
                count / Math.pow(1000, exp),
                "kmgtpe".charAt(exp - 1));
    }
}
