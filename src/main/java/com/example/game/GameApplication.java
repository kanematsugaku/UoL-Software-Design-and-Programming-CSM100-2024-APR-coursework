package com.example.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.game.services.MapLoaderService;
import com.example.game.services.MessageService;

@SpringBootApplication
public class GameApplication implements CommandLineRunner {

    private final MessageService messageService;
    private final MapLoaderService mapLoaderService;

    @Autowired
    public GameApplication(MessageService messageService, MapLoaderService mapLoaderService) {
        this.messageService = messageService;
        this.mapLoaderService = mapLoaderService;
    }

    public static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            messageService.showWelcomeMessage();
            mapLoaderService.loadMap();
            messageService.showClosingMessage();
        } catch (Exception e) {
            messageService.showExceptionMessage(e);
        }
    }
}
