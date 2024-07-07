package com.example.game.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for rolling dice
 */
public class DiceUtil {
    /**
     * Rolls a dice
     *
     * @return the result of the dice roll
     */
    public static int rollDice() {
        return (int) (Math.random() * 6) + 1;
    }

    /**
     * Rolls a dice the specified number of times and returns the highest value. Returns an array of
     * integers representing the dice rolls ordered from highest to lowest.
     *
     * @param times the number of times to roll the dice
     * @return the highest result of the dice rolls
     */
    public static int[] rollDices(int times) {
        List<Integer> rolls = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            rolls.add(rollDice());
        }
        Collections.sort(rolls, Collections.reverseOrder());
        return rolls.stream().mapToInt(Integer::intValue).toArray();
    }
}
