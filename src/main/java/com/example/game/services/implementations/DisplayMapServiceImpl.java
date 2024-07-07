package com.example.game.services.implementations;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.example.game.entities.ContinentEntity;
import com.example.game.entities.CountryEntity;
import com.example.game.entities.MapEntity;
import com.example.game.entities.PlayerEntity;
import com.example.game.services.interfaces.DisplayMapService;
import com.example.game.util.PrintUtil;

/**
 * The service for displaying the map.
 */
@Service
public class DisplayMapServiceImpl implements DisplayMapService {
        /**
         * Displays the map.
         *
         * @param map The map to display.
         * @param players The players to display the map for.
         */
        @Override
        public void display(MapEntity map, List<PlayerEntity> players) {
                PrintUtil.printSpace();
                PrintUtil.printImportantMessage("Map");
                PrintUtil.printSpace();

                List<ContinentEntity> sortedContinents = map.getContinents().stream()
                                .sorted(Comparator.comparing(ContinentEntity::getId)).toList();

                for (ContinentEntity continent : sortedContinents) {

                        PrintUtil.printLine("=== Continent: " + continent.getName() + " ===");

                        List<CountryEntity> sortedCountries = map.getCountries().stream()
                                        .filter(country -> country.getContinentId()
                                                        .equals(continent.getId()))
                                        .sorted(Comparator.comparing(CountryEntity::getId))
                                        .toList();

                        for (CountryEntity country : sortedCountries) {
                                List<CountryEntity> adjacentCountries =
                                                map.getAdjacentCountries(country);
                                String adjacentCountriesString = adjacentCountries.stream()
                                                .map(CountryEntity::getName)
                                                .collect(Collectors.joining(", "));

                                PlayerEntity dominantPlayer = players.stream()
                                                .filter(player -> player.getId()
                                                                .equals(country.getPlayerId()))
                                                .findFirst().orElse(null);

                                PrintUtil.printLine(country.getName() + " | " + "Dominant player: "
                                                + dominantPlayer.getName() + " | " + "Armies: "
                                                + country.getArmyCount() + " | "
                                                + "Adjacent countries: " + adjacentCountriesString);

                        }
                }
        }
}
