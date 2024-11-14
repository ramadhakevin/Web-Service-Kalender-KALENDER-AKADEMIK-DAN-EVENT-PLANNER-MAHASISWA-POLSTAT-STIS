package com.polstat.kalender.util;

public class DateUtils {

    public static int convertMonthStringToNumber(String month) {
        return switch (month.toLowerCase()) {
            case "januari" -> 1;
            case "februari" -> 2;
            case "maret" -> 3;
            case "april" -> 4;
            case "mei" -> 5;
            case "juni" -> 6;
            case "juli" -> 7;
            case "agustus" -> 8;
            case "september" -> 9;
            case "oktober" -> 10;
            case "november" -> 11;
            case "desember" -> 12;
            default -> 0;
        };
    }

    public static String convertMonthNumberToString(int month) {
        return switch (month) {
            case 1 -> "Januari";
            case 2 -> "Februari";
            case 3 -> "Maret";
            case 4 -> "April";
            case 5 -> "Mei";
            case 6 -> "Juni";
            case 7 -> "Juli";
            case 8 -> "Agustus";
            case 9 -> "September";
            case 10 -> "Oktober";
            case 11 -> "November";
            case 12 -> "Desember";
            default -> "Bulan Tidak Valid";
        };
    }
}