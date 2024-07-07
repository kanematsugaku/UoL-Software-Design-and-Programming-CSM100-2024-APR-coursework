package com.example.game.services.implementations;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.springframework.stereotype.Service;
import com.example.game.entities.CountryEntity;
import com.example.game.entities.MapEntity;
import com.example.game.entities.PlayerEntity;
import com.example.game.services.interfaces.DispatchService;
import com.example.game.util.PrintUtil;

/**
 * The service that handles the dispatch.
 *
 * The player may place the troops on any country he owns, divided as he wants.
 */
@Service
public class DispatchServiceImpl implements DispatchService {
    /**
     * Dispatches armies to a country
     *
     * @param scanner the scanner
     * @param player the player
     * @param map the map
     */
    @Override
    public void dispatch(Scanner scanner, MapEntity map, PlayerEntity player) {
        List<CountryEntity> playerCountries = map.getCountriesByPlayer(player);

        // Since the game rule requires at least 1 army to be placed in each country,
        // dispatch 1 army to each country automatically.
        // Player's army count is not reduced for this operation.
        for (CountryEntity country : playerCountries) {
            country.incrementArmyCount();
        }

        // Dispatch armies to countries manually by the player.
        while (player.getArmyCount() > 0) {
            if (player.getType().equals(PlayerEntity.PlayerType.Human)) {
                dispatchManually(scanner, player, map);
            } else {
                dispatchAutomatically(player, map);
            }
        }

        PrintUtil.printSpace();
        PrintUtil.printLine("Dispatch complete. " + player.getName() + "'s countries are:");
        for (CountryEntity country : playerCountries) {
            PrintUtil.printLine(country.getId() + ": " + country.getName() + " ("
                    + country.getArmyCount() + " armies)");
        }
    }

    /**
     * Dispatches armies to a country manually by the player.
     *
     * @param scanner the scanner
     * @param player the player
     * @param map the map
     */
    private void dispatchManually(Scanner scanner, PlayerEntity player, MapEntity map) {
        List<CountryEntity> playerCountries = map.getCountriesByPlayer(player);

        PrintUtil.printSpace();
        PrintUtil.printLine("You have " + player.getArmyCount() + " armies for dispatch.");
        PrintUtil.printLine("You have the following countries:");
        for (CountryEntity country : playerCountries) {
            PrintUtil.printLine(country.getId() + ": " + country.getName() + " ("
                    + country.getArmyCount() + " armies)");
        }

        PrintUtil.printSpace();
        PrintUtil.printLine("Enter the number of country to dispatch 1 army to:");
        Integer countryId = scanner.nextInt();

        CountryEntity country = map.getCountryById(countryId);
        if (!playerCountries.contains(country)) {
            PrintUtil.printLine("Invalid country number. No army dispatched.");
            return;
        }

        country.incrementArmyCount();
        player.decrementArmyCount();
    }

    /**
     * Dispatches armies to a country automatically by the computer.
     *
     * @param player the player
     * @param map the map
     */
    private void dispatchAutomatically(PlayerEntity player, MapEntity map) {
        List<CountryEntity> playerCountries = map.getCountriesByPlayer(player);

        Random random = new Random();
        Integer randomIndex = random.nextInt(playerCountries.size());
        CountryEntity randomCountry = playerCountries.get(randomIndex);

        randomCountry.incrementArmyCount();
        player.decrementArmyCount();
    }
}
