package com.example.game.util;

/**
 * Utility class for generating random values.
 */
public class RandomUtil {
    /**
     * Generate a random boolean value. When no argument is provided, the probability of true is
     * 50%.
     *
     * @return a random boolean value
     */
    public static boolean randomBoolean() {
        return Math.random() < 0.5;
    }

    /**
     * Generate a random boolean value with a given probability.
     *
     * @param probability the probability of true. Range 1 to 99.
     * @return a random boolean value
     */
    public static boolean randomBoolean(int probability) {
        return Math.random() < probability / 100;
    }

    /**
     * Generate a random integer value between min and max.
     *
     * @param min the minimum value
     * @param max the maximum value
     * @return a random integer value between min and max
     */
    public static int randomInt(int min, int max) {
        return min + (int) (Math.random() * (max - min + 1));
    }
}
