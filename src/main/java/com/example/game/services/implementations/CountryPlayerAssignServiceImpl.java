package com.example.game.services.implementations;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.game.entities.CountryEntity;
import com.example.game.entities.MapEntity;
import com.example.game.entities.PlayerEntity;
import com.example.game.services.interfaces.CountryPlayerAssignService;
import com.example.game.util.PrintUtil;

/**
 * The service for assigning players to countries.
 */
@Service
public class CountryPlayerAssignServiceImpl implements CountryPlayerAssignService {
    /**
     * Randomly assigns players to countries.
     *
     * @param map The map to assign players to countries.
     * @param players The players to assign to countries.
     */
    @Override
    public void assign(MapEntity map, List<PlayerEntity> players) {
        var countries = map.getCountries();
        var playerCount = players.size();
        var playerIndex = 0;

        Collections.shuffle(countries);
        for (var country : countries) {
            country.setPlayerId(players.get(playerIndex).getId());
            playerIndex = (playerIndex + 1) % playerCount;
        }
        countries.sort(Comparator.comparing(CountryEntity::getId));

        PrintUtil.printSpace();
        PrintUtil.printLine("Countries assigned to players.");
    }
}
