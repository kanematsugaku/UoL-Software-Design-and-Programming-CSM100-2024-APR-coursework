package com.example.game.services.implementations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Service;
import com.example.game.entities.PlayerEntity;
import com.example.game.entities.PlayerEntity.PlayerType;
import com.example.game.services.interfaces.PlayerInitService;
import com.example.game.util.PrintUtil;

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
     * @param scanner The scanner.
     * @return The list of player entities.
     */
    public List<PlayerEntity> init(Scanner scanner) {
        Integer playerCount = 0;
        boolean isPlayerCountValid = false;

        while (!isPlayerCountValid) {
            PrintUtil.printSpace();
            PrintUtil.printLine("Enter the number of players: ");
            playerCount = scanner.nextInt();
            if (playerCount.equals(AVAILABLE_PLAYER_COUNT)) {
                isPlayerCountValid = true;
            } else {
                PrintUtil.printLine(
                        "Currently, only " + AVAILABLE_PLAYER_COUNT + " players are supported.");
            }
        }

        List<PlayerEntity> players = new ArrayList<>();

        for (int i = 0; i < playerCount; i++) {
            String playerName = "";
            boolean isPlayerNameValid = false;

            while (!isPlayerNameValid) {
                PrintUtil.printSpace();
                PrintUtil.printLine("Enter the name of the player " + (i + 1) + ": ");
                playerName = scanner.next();
                if (!playerName.isEmpty()) {
                    isPlayerNameValid = true;
                } else {
                    PrintUtil.printLine("Player name cannot be empty.");
                }
            }

            PlayerType type = i == 0 ? PlayerType.Human : PlayerType.AI;
            PlayerEntity player = new PlayerEntity(i, playerName, INITIAL_ARMY_COUNT, type);
            players.add(player);
        }

        PrintUtil.printSpace();
        for (int i = 0; i < players.size(); i++) {
            PrintUtil.printLine("Player " + (i + 1) + " name: " + players.get(i).getName());
        }

        return Collections.unmodifiableList(new ArrayList<>(players));
    }
}
