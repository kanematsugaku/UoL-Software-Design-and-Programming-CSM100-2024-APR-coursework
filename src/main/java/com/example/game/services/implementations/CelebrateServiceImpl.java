package com.example.game.services.implementations;

import java.util.List;
import org.springframework.stereotype.Service;
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
    public void celebrate(List<PlayerEntity> players) {
        if (players.size() != 1) {
            throw new IllegalArgumentException("players:" + players);
        }
        PlayerEntity winnerPlayer = players.get(0);
        PrintUtil.printImportantMessage(
                "Congratulations, " + winnerPlayer.getName() + "! You won the game!");
    }
}
