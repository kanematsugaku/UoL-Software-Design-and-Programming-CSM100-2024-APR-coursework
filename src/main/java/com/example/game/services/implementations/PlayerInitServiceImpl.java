package com.example.game.services.implementations;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.game.entities.PlayerEntity;
import com.example.game.entities.PlayerEntity.PlayerType;
import com.example.game.services.interfaces.PlayerInitService;
import com.example.game.util.PrintUtil;
import com.example.game.util.ScannerUtil;

/**
 * The service for initializing players.
 */
@Service
public class PlayerInitServiceImpl implements PlayerInitService {
    private static final int AVAILABLE_PLAYER_COUNT = 2;
    // TODO: Revert to 40 when finalizing this project
    private static final int INITIAL_ARMY_COUNT = 5;

    /**
     * Initializes the players.
     *
     * @return The list of player entities.
     */
    @Override
    public List<PlayerEntity> init() {
        Integer playerCount = 0;
        boolean isPlayerCountValid = false;

        while (!isPlayerCountValid) {
            playerCount =
                    ScannerUtil.scanInt("Enter the number of players: ", "Invalid player count.");
            if (playerCount.equals(AVAILABLE_PLAYER_COUNT)) {
                isPlayerCountValid = true;
            } else {
                PrintUtil.printLine(
                        "Currently, only " + AVAILABLE_PLAYER_COUNT + " players are supported.");
            }
        }

        List<PlayerEntity> players = new ArrayList<>();

        for (int i = 0; i < playerCount; i++) {
            String playerName =
                    ScannerUtil.scanString("Enter the name of the player " + (i + 1) + ": ",
                            "Player name cannot be empty.");

            PlayerType type = i == 0 ? PlayerType.Human : PlayerType.Computer;
            PlayerEntity player = new PlayerEntity(i, playerName, INITIAL_ARMY_COUNT, type);
            players.add(player);
        }

        PrintUtil.printSpace();
        for (int i = 0; i < players.size(); i++) {
            PrintUtil.printLine("Player " + (i + 1) + " name: " + players.get(i).getName());
        }

        return players;
    }
}
