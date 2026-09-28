package com.wildlife;

import java.util.Locale;

public class Format {
    public static String money(double amount) {
        return "R" + String.format(Locale.US, "%,.2f", amount).replace(",", " ");
    }

    public static String yesNo(boolean value) {
        return value ? "Yes" : "No";
    }
}
