package com.example.game.util;

public class PrintUtil {
    public static void printLine(String arg) {
      System.out.println(arg);
    }

    public static void printLines(String... args) {
        for (String arg : args) {
          printLine(arg);
        }
    }
}
