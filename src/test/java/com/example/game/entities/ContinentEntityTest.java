package com.example.game.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ContinentEntityTest {
    private ContinentEntity continentEntity;

    private static final int ID = 1;
    private static final String NAME = "Europe";
    private static final int ARMY_VALUE = 10;
    private static final String COLOR = "BLUE";

    @BeforeEach
    void setUp() {
        continentEntity = new ContinentEntity(ID, NAME, ARMY_VALUE, COLOR);
    }

    @Test
    void testConstructor() {
        var entity = new ContinentEntity(ID, NAME, ARMY_VALUE, COLOR);

        assertEquals(ID, entity.getId());
        assertEquals(NAME, entity.getName());
        assertEquals(ARMY_VALUE, entity.getArmyValue());
        assertEquals(COLOR, entity.getColor());
    }

    @Test
    void testGetId() {
        assertEquals(ID, continentEntity.getId());
    }

    @Test
    void testGetName() {
        assertEquals(NAME, continentEntity.getName());
    }

    @Test
    void testGetArmyValue() {
        assertEquals(ARMY_VALUE, continentEntity.getArmyValue());
    }

    @Test
    void testGetColor() {
        assertEquals(COLOR, continentEntity.getColor());
    }
}
