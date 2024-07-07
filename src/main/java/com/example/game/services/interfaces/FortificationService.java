package com.example.game.services.interfaces;

import java.util.Scanner;
import com.example.game.entities.MapEntity;
import com.example.game.entities.PlayerEntity;

public interface FortificationService {
    void fortify(Scanner scanner, MapEntity map, PlayerEntity player);
}
