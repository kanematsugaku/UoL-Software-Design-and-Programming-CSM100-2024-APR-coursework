package com.example.game.services.implementations;

import java.util.ArrayList;
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
import com.example.game.util.PrintUtil;

/**
 * The service for attacking countries.
 */
@Service
public class AttackServiceImpl implements AttackService {
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

            PrintUtil.printSpace();
            PrintUtil.printLine("You can attack from and to the following countries.");

            List<CountryEntity> sortedFromCountries = attackMap.keySet().stream()
                    .sorted(Comparator.comparing(CountryEntity::getId)).toList();
            for (CountryEntity fromCountry : sortedFromCountries) {
                PrintUtil.printLine("From " + fromCountry.getId() + ": " + fromCountry.getName()
                        + " (" + fromCountry.getArmyCount() + " armies)");

                List<CountryEntity> sortedToCountries = attackMap.get(fromCountry).stream()
                        .sorted(Comparator.comparing(CountryEntity::getId)).toList();
                for (CountryEntity toCountry : sortedToCountries) {
                    PrintUtil.printLine("  " + "To " + toCountry.getId() + ": "
                            + toCountry.getName() + " (" + toCountry.getArmyCount() + " armies)");
                }
            }

            PrintUtil.printSpace();
            PrintUtil.printLine("Do you want to attack? [y/n]: ");
            String answer = scanner.next();

            if (answer.equals("n") || answer.equals("N")) {
                PrintUtil.printLine("Attack ended.");
                break;
            }

            if (answer.equals("y") || answer.equals("Y")) {
                while (true) {
                    PrintUtil.printSpace();
                    PrintUtil.printLine("Enter your country number to attack from: ");
                    Integer fromCountryNumber = scanner.nextInt();
                    CountryEntity fromCountry = map.getCountryById(fromCountryNumber);
                    boolean isPlayerOwnCountry = fromCountry.getPlayerId().equals(player.getId());
                    if (!isPlayerOwnCountry) {
                        PrintUtil.printLine("Invalid country number. Select your country.");
                        break;
                    }
                    if (!fromCountry.canAttackFrom()) {
                        PrintUtil.printLine(
                                "Invalid country number. Select a country that has two or more armies.");
                        break;
                    }

                    PrintUtil.printSpace();
                    PrintUtil.printLine("Enter opponent country number to attack to: ");
                    Integer toCountryNumber = scanner.nextInt();
                    CountryEntity toCountry = map.getCountryById(toCountryNumber);
                    boolean isPlayerNotOwnCountry = !toCountry.getPlayerId().equals(player.getId());
                    if (!isPlayerNotOwnCountry) {
                        PrintUtil.printLine("Invalid country number. Select opponent country.");
                        break;
                    }

                    break;
                }
            }
        }
    }

    private void attackAutomatically(PlayerEntity player, MapEntity map) {
        // TODO: Implement attack automatically
    }

    private Map<CountryEntity, List<CountryEntity>> generateAttackMap(PlayerEntity player,
            MapEntity map) {
        Map<CountryEntity, List<CountryEntity>> attackMap = new HashMap<>();

        List<CountryEntity> playerAttackFromCountries =
                map.getPlayerCountriesWithTwoOrMoreArmies(player);

        if (playerAttackFromCountries.isEmpty()) {
            PrintUtil.printLine("You have no countries that can attack from.");
        } else {
            for (CountryEntity fromCountry : playerAttackFromCountries) {
                List<CountryEntity> adjacentCountries = map.getAdjacentCountries(fromCountry);
                for (CountryEntity adjacentCountry : adjacentCountries) {
                    if (adjacentCountry.getPlayerId() != player.getId()) {
                        if (!attackMap.containsKey(fromCountry)) {
                            attackMap.put(fromCountry, new ArrayList<>());
                        }
                        attackMap.get(fromCountry).add(adjacentCountry);
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
}
