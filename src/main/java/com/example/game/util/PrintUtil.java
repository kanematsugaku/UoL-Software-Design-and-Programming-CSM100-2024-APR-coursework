package com.example.game.util;

/**
 * Utility class for printing messages to the console
 */
public class PrintUtil {
  /**
   * Print arg in a line
   *
   * @param arg message to print
   */
  public static void printLine(String arg) {
    System.out.println(arg);
  }

  /**
   * Print args in lines
   *
   * @param args messages to print
   */
  public static void printLines(String... args) {
    for (String arg : args) {
      printLine(arg);
    }
  }

  /**
   * Print arg in a box
   *
   * @param arg message to print
   */
  public static void printImportantMessage(String arg) {
    printLines("#".repeat(arg.length() + 6), "## " + arg + " ##", "#".repeat(arg.length() + 6));
  }

  /**
   * Print empty line
   *
   * @param count number of empty lines
   */
  public static void printSpace(int count) {
    for (int i = 0; i < count; i++) {
      printLine("");
    }
  }

  /**
   * Print empty line
   *
   * @see #printSpace(int)
   */
  public static void printSpace() {
    printSpace(1);
  }
}
