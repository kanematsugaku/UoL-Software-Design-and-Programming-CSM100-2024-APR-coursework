package com.example.game;

import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.game.entities.MapEntity;
import com.example.game.entities.PlayerEntity;
import com.example.game.services.interfaces.CountryPlayerAssignService;
import com.example.game.services.interfaces.MapInitService;
import com.example.game.services.interfaces.MessageService;
import com.example.game.services.interfaces.PlayerInitService;

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

    @Autowired
    public GameApplication(MessageService messageService, MapInitService mapInitService,
            PlayerInitService playerInitService,
            CountryPlayerAssignService countryPlayerAssignService) {
        this.messageService = messageService;
        this.mapInitService = mapInitService;
        this.playerInitService = playerInitService;
        this.countryPlayerAssignService = countryPlayerAssignService;
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
            this.map = mapInitService.init(scanner);
            this.players = playerInitService.init(scanner);
            countryPlayerAssignService.assign(map, players);

            // -------------------
            // 2. Turn-based play
            // -------------------

            // 2-1. The reinforcement phase
            // TODO: Implement

            // 2-2. The attack phase
            // TODO: Implement

            // 2-3. The fortification phase
            // TODO: Implement

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
