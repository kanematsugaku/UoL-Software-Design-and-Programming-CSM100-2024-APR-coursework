package com.example.game.services.implementations;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.game.entities.ContinentEntity;
import com.example.game.entities.CountryEntity;
import com.example.game.entities.MapEntity;
import com.example.game.entities.PlayerEntity;
import com.example.game.services.interfaces.ReinforcementService;
import com.example.game.util.PrintUtil;

/**
 * The service that handles the reinforcement.
 *
 * In the reinforcement phase, the player is given several armies that depend on the number of
 * countries he owns (divided by three, rounded down).
 *
 * If the player holds all the nations of an entire continent, the player is given several armies
 * corresponding to the continent’s control value.
 *
 * In any case, the minimal number of reinforcement armies is three.
 */
@Service
public class ReinforcementServiceImpl implements ReinforcementService {
    /**
     * Reinforces the armies of a player
     *
     * @param map the map
     * @param player the player
     */
    @Override
    public void reinforce(MapEntity map, PlayerEntity player) {
        Integer ownedCountryReinforcements = 0;
        List<CountryEntity> playerCountries = map.getCountriesByPlayer(player);
        ownedCountryReinforcements = Math.max(3, playerCountries.size() / 3);

        Integer ownedContinentReinforcements = 0;
        for (ContinentEntity continent : map.getContinents()) {
            List<CountryEntity> continentCountries = map.getCountriesByContinent(continent);
            if (playerCountries.containsAll(continentCountries)) {
                ownedContinentReinforcements += continent.getArmyBonus();
            }
        }

        Integer totalReinforcements = ownedCountryReinforcements + ownedContinentReinforcements;

        PrintUtil.printSpace();
        PrintUtil.printLine("Reinforcements for " + player.getName() + ":" + totalReinforcements
                + " (" + "by countries: " + ownedCountryReinforcements + ", " + "by continents: "
                + ownedContinentReinforcements + ")");

        player.addArmyCount(totalReinforcements);
    }
}
