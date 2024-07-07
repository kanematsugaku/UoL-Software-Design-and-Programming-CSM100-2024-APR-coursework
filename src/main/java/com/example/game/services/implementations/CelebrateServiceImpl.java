package com.example.game.services.implementations;

import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.game.entities.ContinentEntity;
import com.example.game.entities.CountryEntity;
import com.example.game.entities.MapEntity;
import com.example.game.entities.PlayerEntity;
import com.example.game.services.interfaces.CelebrateService;
import com.example.game.util.PrintUtil;

/**
 * The service for celebrating the winner.
 */
@Service
public class CelebrateServiceImpl implements CelebrateService {
    /**
     * Show messages for celebrating the winner
     *
     * @param players The list of players.
     */
    @Override
    public void celebrate(MapEntity map, List<PlayerEntity> players) {
        if (players.size() != 1) {
            throw new IllegalArgumentException("players:" + players);
        }
        PlayerEntity winnerPlayer = players.get(0);
        PrintUtil.printImportantMessage(
                "Congratulations, " + winnerPlayer.getName() + " won the game!!");
        PrintUtil.printLine("This is the final map:");

        List<ContinentEntity> sortedContinents = map.getContinents().stream()
                .sorted(Comparator.comparing(ContinentEntity::getId)).toList();

        for (ContinentEntity continent : sortedContinents) {
            PrintUtil.printLine("=== Continent: " + continent.getName() + " ===");

            List<CountryEntity> sortedCountries = map.getCountries().stream()
                    .filter(country -> country.getContinentId().equals(continent.getId()))
                    .sorted(Comparator.comparing(CountryEntity::getId)).toList();

            for (CountryEntity country : sortedCountries) {
                PrintUtil
                        .printLine(country.getName() + " | " + "Armies: " + country.getArmyCount());

            }
        }
    }
}
