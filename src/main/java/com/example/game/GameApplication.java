package com.example.game;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.game.entities.MapEntity;
import com.example.game.entities.PlayerEntity;
import com.example.game.services.interfaces.AttackService;
import com.example.game.services.interfaces.CelebrateService;
import com.example.game.services.interfaces.CheckGameEndService;
import com.example.game.services.interfaces.CountryPlayerAssignService;
import com.example.game.services.interfaces.DispatchService;
import com.example.game.services.interfaces.DisplayMapService;
import com.example.game.services.interfaces.FortificationService;
import com.example.game.services.interfaces.MapInitService;
import com.example.game.services.interfaces.MessageService;
import com.example.game.services.interfaces.PlayerInitService;
import com.example.game.services.interfaces.ReinforcementService;
import com.example.game.services.interfaces.RemovePlayerService;
import com.example.game.util.ScannerUtil;

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
    private final DisplayMapService displayMapService;
    private final CountryPlayerAssignService countryPlayerAssignService;
    private final ReinforcementService reinforcementService;
    private final DispatchService dispatchService;
    private final AttackService attackService;
    private final FortificationService fortificationService;
    private final RemovePlayerService removePlayerService;
    private final CheckGameEndService checkGameEndService;
    private final CelebrateService celebrateService;
    private boolean isGameEnded = false;

    @Autowired
    public GameApplication(MessageService messageService, MapInitService mapInitService,
            PlayerInitService playerInitService, DisplayMapService displayMapService,
            CountryPlayerAssignService countryPlayerAssignService,
            ReinforcementService reinforcementService, DispatchService dispatchService,
            AttackService attackService, FortificationService fortificationService,
            RemovePlayerService removePlayerService, CheckGameEndService checkGameEndService,
            CelebrateService celebrateService) {
        this.messageService = messageService;
        this.mapInitService = mapInitService;
        this.playerInitService = playerInitService;
        this.displayMapService = displayMapService;
        this.countryPlayerAssignService = countryPlayerAssignService;
        this.reinforcementService = reinforcementService;
        this.dispatchService = dispatchService;
        this.attackService = attackService;
        this.fortificationService = fortificationService;
        this.removePlayerService = removePlayerService;
        this.checkGameEndService = checkGameEndService;
        this.celebrateService = celebrateService;
    }

    public static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            messageService.showWelcomeMessage();

            // -----------------
            // 1. Startup phase
            // -----------------
            map = mapInitService.init();
            players = playerInitService.init();
            countryPlayerAssignService.assign(map, players);

            // --------------------
            // 2. Turn-based phase
            // --------------------
            while (!isGameEnded) {
                // 2-1. The reinforcement phase
                for (PlayerEntity player : players) {
                    displayMapService.display(map, players);
                    reinforcementService.reinforce(map, player);
                    dispatchService.dispatch(map, player);
                }

                // 2-2. The attack phase
                for (PlayerEntity player : players) {
                    displayMapService.display(map, players);
                    attackService.attack(map, player);

                }

                // 2-3. The fortification phase
                for (PlayerEntity player : players) {
                    displayMapService.display(map, players);
                    fortificationService.fortify(map, player);
                }

                // 2-4. Turn-ending phase
                removePlayerService.remove(map, players);
                isGameEnded = checkGameEndService.check(map, players);
            }

            // ---------------------
            // 3. The endgame phase
            // ---------------------
            displayMapService.display(map, players);
            celebrateService.celebrate(players);
        } catch (Exception e) {
            messageService.showExceptionMessage(e);
        } finally {
            ScannerUtil.dispose();
            messageService.showClosingMessage();
        }
    }
}
