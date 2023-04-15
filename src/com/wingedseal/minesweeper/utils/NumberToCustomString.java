package com.wingedseal.minesweeper.utils;

public class NumberToCustomString {
    public static String toCustomString(int number) {
        return String.format("%03d", number < 0 ? number % 100 : number % 1000);
    }
}
