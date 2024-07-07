package com.example.game.services.implementations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.stereotype.Service;
import com.example.game.entities.CountryEntity;
import com.example.game.entities.MapEntity;
import com.example.game.entities.PlayerEntity;
import com.example.game.services.interfaces.AttackService;
import com.example.game.util.DiceUtil;
import com.example.game.util.PrintUtil;
import com.example.game.util.RandomUtil;

/**
 * The service for attacking countries.
 */
@Service
public class AttackServiceImpl implements AttackService {
    private static final int ATTACKER_COUNTRY_MAX_DICE = 3;
    private static final int DEFENDER_COUNTRY_MAX_DICE = 2;

    /**
     * Attacks a country.
     *
     * @param scanner the scanner
     * @param player the player
     * @param map the map
     */
    public void attack(Scanner scanner, PlayerEntity player, MapEntity map) {
        if (player.getType().equals(PlayerEntity.PlayerType.Human)) {
            attackManually(scanner, player, map);
        } else {
            attackAutomatically(player, map);
        }
        PrintUtil.printLine(player.getName() + " attack completed.");
    }

    /**
     * Attacks a country manually by the player.
     *
     * @param scanner the scanner
     * @param player the player
     * @param map the map
     */
    private void attackManually(Scanner scanner, PlayerEntity player, MapEntity map) {
        while (true) {
            Map<CountryEntity, List<CountryEntity>> attackMap = generateAttackMap(player, map);
            if (attackMap.isEmpty()) {
                break;
            }

            PrintUtil.printSpace();
            PrintUtil.printLine("You can attack from/to the following countries.");
            showAttackMap(attackMap);

            PrintUtil.printSpace();
            PrintUtil.printLine("Do you want to attack? [y/n]: ");
            String answerForAttack = scanner.next();

            if (answerForAttack.equals("n") || answerForAttack.equals("N")) {
                PrintUtil.printLine("Attack ended.");
                break;
            }

            if (answerForAttack.equals("y") || answerForAttack.equals("Y")) {
                PrintUtil.printLine("You can attack from/to the following countries.");
                showAttackMap(attackMap);
                PrintUtil.printSpace();
                PrintUtil.printLine("Enter your country number to attack from: ");
                Integer attackerCountryNumber = scanner.nextInt();
                CountryEntity attackerCountry = map.getCountryById(attackerCountryNumber);
                boolean isPlayerOwnCountry = attackerCountry.getPlayerId().equals(player.getId());
                if (!isPlayerOwnCountry) {
                    PrintUtil.printLine("Invalid country number. Select your country.");
                    break;
                }
                if (!attackerCountry.canAttack()) {
                    PrintUtil.printLine(
                            "Invalid country number. Select a country that has two or more armies.");
                    break;
                }

                PrintUtil.printSpace();
                PrintUtil.printLine("Enter opponent country number to attack to: ");
                Integer defenderCountryNumber = scanner.nextInt();
                CountryEntity defenderCountry = map.getCountryById(defenderCountryNumber);
                boolean isPlayerNotOwnCountry =
                        !defenderCountry.getPlayerId().equals(player.getId());
                if (!isPlayerNotOwnCountry) {
                    PrintUtil.printLine("Invalid country number. Select opponent country.");
                    break;
                }

                rollDiceLoop: {
                    while (true) {
                        battle(attackerCountry, defenderCountry, map);

                        if (defenderCountry.getArmyCount() == 0
                                || attackerCountry.getArmyCount() == 0) {
                            break;
                        }

                        if (!attackerCountry.canAttack()) {
                            PrintUtil.printLine(
                                    "Your country has no armies to attack. Two or more armies are required to attack.");
                            break;
                        }

                        while (true) {
                            PrintUtil.printSpace();
                            PrintUtil.printLine(
                                    "Do you want to attack again to the same country? [y/n]: ");
                            String answerForAttackAgain = scanner.next();
                            if (answerForAttackAgain.equals("n")
                                    || answerForAttackAgain.equals("N")) {
                                break rollDiceLoop;
                            }
                            if (answerForAttackAgain.equals("y")
                                    || answerForAttackAgain.equals("Y")) {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Attacks a country automatically by the computer.
     *
     * @param player the player
     * @param map the map
     */
    private void attackAutomatically(PlayerEntity player, MapEntity map) {
        while (RandomUtil.randomBoolean()) {
            Map<CountryEntity, List<CountryEntity>> attackMap = generateAttackMap(player, map);
            if (attackMap.isEmpty()) {
                break;
            }

            CountryEntity randomlySelectedCountry = attackMap.keySet().stream()
                    .skip(RandomUtil.randomInt(0, attackMap.size())).findFirst().orElse(null);

            if (randomlySelectedCountry == null) {
                break;
            }

            CountryEntity randomlySelectedDefenderCountry = attackMap.get(randomlySelectedCountry)
                    .stream()
                    .skip(RandomUtil.randomInt(0, attackMap.get(randomlySelectedCountry).size()))
                    .findFirst().orElse(null);

            if (randomlySelectedDefenderCountry == null) {
                break;
            }

            battle(randomlySelectedCountry, randomlySelectedDefenderCountry, map);
        }
    }

    /**
     * Generates the attack map for the player or the computer.
     *
     * @param player the player
     * @param map the map
     * @return the attack map
     */
    private Map<CountryEntity, List<CountryEntity>> generateAttackMap(PlayerEntity player,
            MapEntity map) {
        Map<CountryEntity, List<CountryEntity>> attackMap = new HashMap<>();

        List<CountryEntity> playerAttackFromCountries =
                map.getPlayerCountriesWithTwoOrMoreArmies(player);

        if (playerAttackFromCountries.isEmpty()) {
            PrintUtil.printLine("You have no countries that can attack from.");
        } else {
            for (CountryEntity attackerCountry : playerAttackFromCountries) {
                List<CountryEntity> adjacentCountries = map.getAdjacentCountries(attackerCountry);
                for (CountryEntity adjacentCountry : adjacentCountries) {
                    if (adjacentCountry.getPlayerId() != player.getId()) {
                        if (!attackMap.containsKey(attackerCountry)) {
                            attackMap.put(attackerCountry, new ArrayList<>());
                        }
                        attackMap.get(attackerCountry).add(adjacentCountry);
                    }
                }
            }

            if (attackMap.isEmpty()) {
                PrintUtil.printLine(
                        "There are no opponent countries connected to your countries to attack to.");
            }
        }

        return attackMap;
    }

    /**
     * Shows the attack map.
     *
     * @param attackMap the attack map
     */
    private void showAttackMap(Map<CountryEntity, List<CountryEntity>> attackMap) {
        List<CountryEntity> sortedAttackerCountries = attackMap.keySet().stream()
                .sorted(Comparator.comparing(CountryEntity::getId)).toList();
        for (CountryEntity attackerCountry : sortedAttackerCountries) {
            PrintUtil.printLine("From " + attackerCountry.getId() + ": " + attackerCountry.getName()
                    + " (" + attackerCountry.getArmyCount() + " armies)");

            List<CountryEntity> sortedDefenderCountries = attackMap.get(attackerCountry).stream()
                    .sorted(Comparator.comparing(CountryEntity::getId)).toList();
            for (CountryEntity defenderCountry : sortedDefenderCountries) {
                PrintUtil.printLine(
                        "  " + "To " + defenderCountry.getId() + ": " + defenderCountry.getName()
                                + " (" + defenderCountry.getArmyCount() + " armies)");
            }
        }
    }

    /**
     * Rolls the dice for the attacker and defender countries and determines the outcome of the
     * attack.
     *
     * @param attackerCountry the attacker country
     * @param defenderCountry the defender country
     * @param map the map
     */
    private void battle(CountryEntity attackerCountry, CountryEntity defenderCountry,
            MapEntity map) {
        int attackerCountryArmyCount = attackerCountry.getArmyCount();
        int attackerCountryDiceCount =
                Math.min(attackerCountryArmyCount, ATTACKER_COUNTRY_MAX_DICE);
        int[] attackerCountryDiceNumber = DiceUtil.rollDices(attackerCountryDiceCount);

        int defenderCountryArmyCount = defenderCountry.getArmyCount();
        int defenderCountryDiceCount =
                Math.min(defenderCountryArmyCount, DEFENDER_COUNTRY_MAX_DICE);
        int[] defenderCountryDiceNumber = DiceUtil.rollDices(defenderCountryDiceCount);

        PrintUtil.printLine("Attacker dice: " + Arrays.toString(attackerCountryDiceNumber));
        PrintUtil.printLine("Defender dice: " + Arrays.toString(defenderCountryDiceNumber));

        // The outcome of the attack is determined by comparing the defender’s best dice roll with
        // the attacker’s best dice roll. If the defender rolls greater or equal to the attacker,
        // the attacker loses and defender wins.
        int attackerCountryHighestDiceNumber = attackerCountryDiceNumber[0];
        int defenderCountryHighestDiceNumber = defenderCountryDiceNumber[0];
        boolean isAttackerWinByHighestDice =
                attackerCountryHighestDiceNumber > defenderCountryHighestDiceNumber;

        if (isAttackerWinByHighestDice) {
            PrintUtil.printLine("Attacker wins by highest dice.");
            defenderCountry.decrementArmyCount();
        } else {
            PrintUtil.printLine("Defender wins by highest dice.");
            attackerCountry.decrementArmyCount();
        }

        // If the defender rolls two dice, then his other dice roll is compared to the attacker’s
        // second-best dice roll and a second army is lost by the attacker or defender in the same
        // way.
        if (defenderCountryDiceCount >= 2) {
            int attackerCountrySecondHighestDiceNumber = attackerCountryDiceNumber[1];
            int defenderCountrySecondHighestDiceNumber = defenderCountryDiceNumber[1];
            boolean isAttackerWinBySecondHighestDice =
                    attackerCountrySecondHighestDiceNumber > defenderCountrySecondHighestDiceNumber;
            if (isAttackerWinBySecondHighestDice) {
                PrintUtil.printLine("Attacker wins by second highest dice.");
                defenderCountry.decrementArmyCount();
            } else {
                PrintUtil.printLine("Defender wins by second highest dice.");
                attackerCountry.decrementArmyCount();
            }
        }

        PrintUtil.printLine("Your country armies    : " + attackerCountry.getArmyCount());
        PrintUtil.printLine("Opponent country armies: " + defenderCountry.getArmyCount());

        if (defenderCountry.getArmyCount() == 0) {
            PrintUtil.printLine("Your dominate the opponent country.");
            defenderCountry.setPlayerId(attackerCountry.getPlayerId());
        }
        if (attackerCountry.getArmyCount() == 0) {
            PrintUtil.printLine("Opponent dominate your country.");
            attackerCountry.setPlayerId(defenderCountry.getPlayerId());
        }
    }
}
