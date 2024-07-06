package com.example.game.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CountryEntityTest {

    @Test
    void testConstructor() {
        var id = 1;
        var name = "Europe";
        var continentId = 1;
        var xCoordinate = 1;
        var yCoordinate = 1;

        var entity = new CountryEntity(id, name, continentId, xCoordinate, yCoordinate);

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(continentId, entity.getContinentId());
        assertEquals(xCoordinate, entity.getXCoordinate());
        assertEquals(yCoordinate, entity.getYCoordinate());
    }
}
