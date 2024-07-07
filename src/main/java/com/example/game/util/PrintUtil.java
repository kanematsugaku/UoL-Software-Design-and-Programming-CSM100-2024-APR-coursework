package com.example.game.util;

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

  /**
   * Clear screen
   *
   * Caution: Only works on Unix-like systems
   *
   * @see https://techno-terminal.blogspot.com/2014/12/clear-command-line-console-and-bold.html
   */
  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
