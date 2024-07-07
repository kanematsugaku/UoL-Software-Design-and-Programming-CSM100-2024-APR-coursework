package com.example.game.services.interfaces;

import java.util.List;
import com.example.game.entities.MapEntity;
import com.example.game.entities.PlayerEntity;

public interface RemovePlayerService {
    void remove(MapEntity map, List<PlayerEntity> players);
}
