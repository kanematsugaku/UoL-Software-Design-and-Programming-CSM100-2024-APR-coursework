package com.example.game.services.interfaces;

import java.util.Scanner;
import com.example.game.entities.MapEntity;

public interface MapInitService {
    MapEntity init(Scanner scanner) throws Exception;
}
