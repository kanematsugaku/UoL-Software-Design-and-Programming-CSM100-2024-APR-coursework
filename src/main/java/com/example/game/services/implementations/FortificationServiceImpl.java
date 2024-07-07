package com.example.game.services.implementations;

import java.util.Scanner;
import org.springframework.stereotype.Service;
import com.example.game.entities.MapEntity;
import com.example.game.entities.PlayerEntity;
import com.example.game.services.interfaces.FortificationService;
import com.example.game.util.PrintUtil;

/**
 * The service for fortifying countries.
 */
@Service
public class FortificationServiceImpl implements FortificationService {
    /**
     * Fortifies a country.
     *
     * @param scanner the scanner
     * @param map the map
     * @param player the player
     */
    public void fortify(Scanner scanner, MapEntity map, PlayerEntity player) {
        if (player.getType().equals(PlayerEntity.PlayerType.Human)) {
            fortifyManually(scanner, player, map);
        } else {
            fortifyAutomatically(player, map);
        }
        PrintUtil.printLine(player.getName() + " fortification completed.");
    }

    /**
     * Fortifies a country manually by the player.
     *
     * @param scanner the scanner
     * @param player the player
     * @param map
     */
    public void fortifyManually(Scanner scanner, PlayerEntity player, MapEntity map) {

    }

    /**
     * Fortifies a country automatically by the computer.
     *
     * @param player the player
     * @param map the map
     */
    public void fortifyAutomatically(PlayerEntity player, MapEntity map) {

    }
}
