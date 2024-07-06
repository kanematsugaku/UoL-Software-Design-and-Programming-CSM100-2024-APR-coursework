package com.example.game;

import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.game.entities.MapEntity;
import com.example.game.entities.PlayerEntity;
import com.example.game.services.interfaces.AttackService;
import com.example.game.services.interfaces.CountryPlayerAssignService;
import com.example.game.services.interfaces.DispatchService;
import com.example.game.services.interfaces.MapInitService;
import com.example.game.services.interfaces.MessageService;
import com.example.game.services.interfaces.PlayerInitService;
import com.example.game.services.interfaces.ReinforcementService;

/**
 * The entry point class for the game application.
 */
@SpringBootApplication
public class GameApplication implements CommandLineRunner {

    private MapEntity map;
    private List<PlayerEntity> players;
    private final MessageService messageService;
    private final MapInitService mapInitService;
    private final PlayerInitService playerInitService;
    private final CountryPlayerAssignService countryPlayerAssignService;
    private final ReinforcementService reinforcementService;
    private final DispatchService dispatchService;
    private final AttackService attackService;
    private boolean isGameEnded = false;

    @Autowired
    public GameApplication(MessageService messageService, MapInitService mapInitService,
            PlayerInitService playerInitService,
            CountryPlayerAssignService countryPlayerAssignService,
            ReinforcementService reinforcementService, DispatchService dispatchService,
            AttackService attackService) {
        this.messageService = messageService;
        this.mapInitService = mapInitService;
        this.playerInitService = playerInitService;
        this.countryPlayerAssignService = countryPlayerAssignService;
        this.reinforcementService = reinforcementService;
        this.dispatchService = dispatchService;
        this.attackService = attackService;
    }

    public static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        try {
            messageService.showWelcomeMessage();

            // -----------------
            // 1. Startup phase
            // -----------------
            map = mapInitService.init(scanner);
            players = playerInitService.init(scanner);
            countryPlayerAssignService.assign(map, players);

            // -------------------
            // 2. Turn-based play
            // -------------------
            while (!isGameEnded) {
                // 2-1. The reinforcement phase
                for (PlayerEntity player : players) {
                    reinforcementService.reinforce(player, map);
                    dispatchService.dispatch(scanner, player, map);
                }

                // 2-2. The attack phase
                for (PlayerEntity player : players) {
                    attackService.attack(scanner, player, map);
                }

                isGameEnded = true;

                // 2-3. The fortification phase
                // TODO: Implement
            }

            // ---------------
            // 3. The endgame
            // ---------------

        } catch (Exception e) {
            messageService.showExceptionMessage(e);
        } finally {
            scanner.close();
            messageService.showClosingMessage();
        }
    }
}
