package com.example.game.services.interfaces;

import java.util.List;
import com.example.game.entities.MapEntity;
import com.example.game.entities.PlayerEntity;

public interface CheckGameEndService {
    boolean check(MapEntity map, List<PlayerEntity> players);
}
