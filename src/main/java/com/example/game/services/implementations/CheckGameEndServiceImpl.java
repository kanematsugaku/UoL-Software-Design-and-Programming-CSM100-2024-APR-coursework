package com.example.game.services.implementations;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.game.entities.MapEntity;
import com.example.game.entities.PlayerEntity;
import com.example.game.services.interfaces.CheckGameEndService;

/**
 * The service for checking if the game is ended.
 */
@Service
public class CheckGameEndServiceImpl implements CheckGameEndService {
    /**
     * Check if the game is ended.
     *
     * @param map The map.
     * @param players The players.
     * @return true if the game is ended, false otherwise.
     */
    @Override
    public boolean check(MapEntity map, List<PlayerEntity> players) {
        // Only one player means all the countries are owned by one player
        return players.size() == 1;
    }
}
