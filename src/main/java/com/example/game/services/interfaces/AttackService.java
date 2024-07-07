package com.example.game.services.interfaces;

import com.example.game.entities.MapEntity;
import com.example.game.entities.PlayerEntity;

public interface AttackService {
    void attack(MapEntity map, PlayerEntity player);
}
