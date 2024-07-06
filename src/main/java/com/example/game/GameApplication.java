package com.example.game;

import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.game.entities.MapEntity;
import com.example.game.entities.PlayerEntity;
import com.example.game.services.MapInitService;
import com.example.game.services.MessageService;
import com.example.game.services.PlayerInitService;

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

    @Autowired
    public GameApplication(MessageService messageService, MapInitService mapInitService,
            PlayerInitService playerInitService) {
        this.messageService = messageService;
        this.mapInitService = mapInitService;
        this.playerInitService = playerInitService;
    }

    public static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        try {
            messageService.showWelcomeMessage();

            // Startup phase
            this.map = mapInitService.init(scanner);
            this.players = playerInitService.init(scanner, 2);
        } catch (Exception e) {
            messageService.showExceptionMessage(e);
        } finally {
            scanner.close();
            messageService.showClosingMessage();
        }
    }
}
