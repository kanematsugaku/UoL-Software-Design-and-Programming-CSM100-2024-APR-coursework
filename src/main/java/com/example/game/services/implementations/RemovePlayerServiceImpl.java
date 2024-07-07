package com.example.game.services.implementations;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.game.entities.CountryEntity;
import com.example.game.entities.MapEntity;
import com.example.game.entities.PlayerEntity;
import com.example.game.services.interfaces.RemovePlayerService;

/**
 * The service for removing players from the game.
 */
@Service
public class RemovePlayerServiceImpl implements RemovePlayerService {
    /**
     * Remove players from the game.
     *
     * @param map The map.
     * @param players The players.
     */
    @Override
    public void remove(MapEntity map, List<PlayerEntity> players) {
        List<PlayerEntity> playersToRemove = new ArrayList<>();

        for (PlayerEntity player : players) {
            boolean isPlayerOwnsCountry = false;

            for (CountryEntity country : map.getCountries()) {
                if (country.getPlayerId().equals(player.getId())) {
                    isPlayerOwnsCountry = true;
                    break;
                }
            }

            if (!isPlayerOwnsCountry) {
                playersToRemove.add(player);
            }
        }

        players.removeAll(playersToRemove);
    }
}
