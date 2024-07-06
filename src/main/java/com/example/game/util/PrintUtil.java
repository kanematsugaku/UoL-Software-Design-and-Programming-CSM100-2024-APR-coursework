package com.example.game.util;

public class PrintUtil {
  /**
   * Print arg in a line
   */
  public static void printLine(String arg) {
    System.out.println(arg);
  }

  /**
   * Print args in lines
   */
  public static void printLines(String... args) {
    for (String arg : args) {
      printLine(arg);
    }
  }

  /**
   * Print arg in a box
   */
  public static void printImportantMessage(String arg) {
    printLines("#".repeat(arg.length() + 6), "## " + arg + " ##", "#".repeat(arg.length() + 6));
  }
}
