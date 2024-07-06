package com.example.game.services.interfaces;

import java.util.Scanner;
import com.example.game.entities.MapEntity;
import com.example.game.entities.PlayerEntity;

public interface AttackService {
    void attack(Scanner scanner, PlayerEntity player, MapEntity map);
}
