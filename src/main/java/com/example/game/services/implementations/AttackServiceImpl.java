package com.example.game.services.implementations;

import java.util.ArrayList;
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
     * Attack a country.
     *
     * @param scanner
     * @param player
     * @param map
     */
    public void attack(Scanner scanner, PlayerEntity player, MapEntity map) {
        List<CountryEntity> playerAttackBaseCountries =
                map.getPlayerCountriesWithTwoOrMoreArmies(player);

        // キーバリューのマップを作成する。
        // キー: 攻撃元の国, 値: 攻撃先の国
        Map<CountryEntity, List<CountryEntity>> attackMap = new HashMap<>();

        if (playerAttackBaseCountries.isEmpty()) {
            PrintUtil.printLine("You have no countries that can be base for attack.");
            return;
        }

        for (CountryEntity baseCountry : playerAttackBaseCountries) {
            List<CountryEntity> adjacentCountries = map.getAdjacentCountries(baseCountry);
            for (CountryEntity adjacentCountry : adjacentCountries) {
                if (adjacentCountry.getPlayerId() != player.getId()) {
                    if (!attackMap.containsKey(baseCountry)) {
                        attackMap.put(baseCountry, new ArrayList<>());
                    }
                    attackMap.get(baseCountry).add(adjacentCountry);
                }
            }
        }

        if (attackMap.isEmpty()) {
            PrintUtil
                    .printLine("There are no opponent countries connected to your base countries.");
            return;
        }

        PrintUtil.printLine("You can attack from and to the following countries:");
        for (CountryEntity baseCountry : attackMap.keySet()) {
            PrintUtil.printLine("From " + baseCountry.getId() + ": " + baseCountry.getName() + " ("
                    + baseCountry.getArmyCount() + " armies)");

            for (CountryEntity attackableCountry : attackMap.get(baseCountry)) {
                PrintUtil.printLine("  " + "To " + attackableCountry.getId() + ": "
                        + attackableCountry.getName() + " (" + attackableCountry.getArmyCount()
                        + " armies)");
            }
        }

        // TODO: Receive input from player and attack.
    }
}
