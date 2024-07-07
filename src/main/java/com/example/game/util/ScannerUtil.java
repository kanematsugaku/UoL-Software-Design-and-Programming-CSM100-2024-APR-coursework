package com.example.game.util;

import java.util.Scanner;

/**
 * Util class for scanning user input
 */
public class ScannerUtil {
    private static final Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    /**
     * Dispose the scanner
     */
    public static void dispose() {
        scanner.close();
    }

    /**
     * Scans a string from the user
     *
     * @param promptMessage The message to prompt the user
     * @param errorMessage The message to prompt the user if the input is invalid
     * @return The input from the user
     */
    public static String scanString(String promptMessage, String errorMessage) {
        String input = null;
        boolean isInputValid = false;

        while (!isInputValid) {
            PrintUtil.printSpace();
            PrintUtil.printLine(promptMessage);
            input = scanner.nextLine();
            if (input != null && input.matches("^[a-zA-Z]+$")) {
                isInputValid = true;
            } else {
                PrintUtil.printLine(errorMessage);
            }
        }

        return input;
    }

    /**
     * Scans a string from the user
     *
     * @param promptMessage The message to prompt the user
     * @return The input from the user
     */
    public static String scanString(String promptMessage) {
        String input = null;
        boolean isInputValid = false;

        while (!isInputValid) {
            PrintUtil.printSpace();
            PrintUtil.printLine(promptMessage);
            input = scanner.nextLine();
            if (input != null && input.matches("^[a-zA-Z]+$")) {
                isInputValid = true;
            }
        }

        return input;
    }

    /**
     * Scans an integer from the user
     *
     * @param promptMessage The message to prompt the user
     * @param errorMessage The message to prompt the user if the input is invalid
     * @return The input from the user
     */
    public static int scanInt(String promptMessage, String errorMessage) {
        String input = null;
        boolean isInputValid = false;

        while (!isInputValid) {
            PrintUtil.printSpace();
            PrintUtil.printLine(promptMessage);
            input = scanner.nextLine();
            if (input != null && input.matches("^[0-9]+$")) {
                isInputValid = true;
            } else {
                PrintUtil.printLine(errorMessage);
            }
        }

        return Integer.parseInt(input);
    }

    /**
     * Scans an integer from the user
     *
     * @param promptMessage The message to prompt the user
     * @return The input from the user
     */
    public static int scanInt(String promptMessage) {
        String input = null;
        boolean isInputValid = false;

        while (!isInputValid) {
            PrintUtil.printSpace();
            PrintUtil.printLine(promptMessage);
            input = scanner.nextLine();
            if (input != null && input.matches("^[0-9]+$")) {
                isInputValid = true;
            }
        }

        return Integer.parseInt(input);
    }
}
