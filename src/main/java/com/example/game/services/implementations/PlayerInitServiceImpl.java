package com.example.game.services.implementations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Service;
import com.example.game.entities.PlayerEntity;
import com.example.game.services.interfaces.PlayerInitService;
import com.example.game.util.PrintUtil;

/**
 * The service for initializing players.
 */
@Service
public class PlayerInitServiceImpl implements PlayerInitService {
    /**
     * Initializes the players.
     *
     * @param scanner The scanner.
     * @return The list of player entities.
     */
    public List<PlayerEntity> init(Scanner scanner) {
        int playerCount = 0;
        boolean isPlayerCountValid = false;

        while (!isPlayerCountValid) {
            PrintUtil.printLine("Enter the number of players: ");
            playerCount = scanner.nextInt();
            if (playerCount == 2) {
                isPlayerCountValid = true;
            } else {
                PrintUtil.printLine("Currently, only 2 players are supported.");
            }
        }

        List<PlayerEntity> players = new ArrayList<>();

        for (int i = 0; i < playerCount; i++) {
            String playerName = "";
            boolean isPlayerNameValid = false;

            while (!isPlayerNameValid) {
                PrintUtil.printLine("Enter the name of the player " + (i + 1) + ": ");
                playerName = scanner.next();
                if (!playerName.isEmpty()) {
                    isPlayerNameValid = true;
                } else {
                    PrintUtil.printLine("Player name cannot be empty.");
                }
            }

            PlayerEntity player = new PlayerEntity(i, playerName, 40);
            players.add(player);
        }

        for (int i = 0; i < players.size(); i++) {
            PrintUtil.printLine("Player " + (i + 1) + " name: " + players.get(i).getName());
        }

        return Collections.unmodifiableList(new ArrayList<>(players));
    }
}
