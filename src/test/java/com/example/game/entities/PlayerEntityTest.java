package com.example.game.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlayerEntityTest {

    @Test
    void testConstructor() {
        var name = "Alice";
        var armyCount = 1;

        var entity = new PlayerEntity(name, armyCount);

        assertEquals(name, entity.name);
        assertEquals(armyCount, entity.armyCount);
    }
}
