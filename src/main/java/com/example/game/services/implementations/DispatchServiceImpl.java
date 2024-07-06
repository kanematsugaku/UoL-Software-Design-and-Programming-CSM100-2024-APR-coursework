package com.example.game.services.implementations;

import java.util.List;
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
    public void dispatch(Scanner scanner, PlayerEntity player, MapEntity map) {
        List<CountryEntity> playerCountries = map.getPlayerCountries(player);

        // Since the game rule requires at least 1 army to be placed in each country,
        // dispatch 1 army to each country automatically.
        // Player's army count is not reduced for this operation.
        for (CountryEntity country : playerCountries) {
            country.incrementArmyCount();
        }

        // Dispatch armies to countries manually by the player.
        while (player.getArmyCount() > 0) {
            PrintUtil.printLine("You have " + player.getArmyCount() + " armies for dispatch.");
            PrintUtil.printLine("You have the following countries:");
            for (CountryEntity country : playerCountries) {
                PrintUtil.printLine(country.getId() + ": " + country.getName() + " ("
                        + country.getArmyCount() + " armies)");
            }

            PrintUtil.printLine("Enter the number of country to dispatch 1 army to:");
            int countryId = scanner.nextInt();

            CountryEntity country = map.getCountryById(countryId);
            if (!playerCountries.contains(country)) {
                PrintUtil.printLine("Invalid country number.");
                continue;
            }

            country.incrementArmyCount();
            player.decrementArmyCount();
        }

        PrintUtil.printLine("Dispatch complete. Now your countries are:");
        for (CountryEntity country : playerCountries) {
            PrintUtil.printLine(country.getId() + ": " + country.getName() + " ("
                    + country.getArmyCount() + " armies)");
        }
    }
}
