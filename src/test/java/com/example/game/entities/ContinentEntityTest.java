package com.example.game.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ContinentEntityTest {

    @Test
    void testConstructor() {
        var id = 1;
        var name = "Europe";
        var armyValue = 10;
        var color = "";

        var entity = new ContinentEntity(id, name, armyValue, color);

        assertEquals(id, entity.id);
        assertEquals(name, entity.name);
        assertEquals(armyValue, entity.armyValue);
        assertEquals(color, entity.color);
    }
}
