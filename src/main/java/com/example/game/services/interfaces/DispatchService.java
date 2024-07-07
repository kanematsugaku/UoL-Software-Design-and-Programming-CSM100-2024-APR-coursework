package com.example.game.services.interfaces;

import com.example.game.entities.MapEntity;
import com.example.game.entities.PlayerEntity;

public interface DispatchService {
    void dispatch(MapEntity map, PlayerEntity player);
}
