package com.example.game.services.interfaces;

import java.util.List;
import java.util.Scanner;
import com.example.game.entities.PlayerEntity;

public interface PlayerInitService {
    List<PlayerEntity> init(Scanner scanner, int playerCount);
}
