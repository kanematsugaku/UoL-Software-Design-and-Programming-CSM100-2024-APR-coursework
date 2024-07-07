package com.example.game.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CountryEntityTest {
    private CountryEntity countryEntity;

    private static final int ID = 1;
    private static final String NAME = "UK";
    private static final int CONTINENT_ID = 1;
    private static final int X_COORDINATE = 1;
    private static final int Y_COORDINATE = 1;

    @BeforeEach
    void setUp() {
        countryEntity = new CountryEntity(ID, NAME, CONTINENT_ID, X_COORDINATE, Y_COORDINATE);
    }

    @Test
    void testConstructor() {
        var entity = new CountryEntity(ID, NAME, CONTINENT_ID, X_COORDINATE, Y_COORDINATE);

        assertEquals(ID, entity.getId());
        assertEquals(NAME, entity.getName());
        assertEquals(CONTINENT_ID, entity.getContinentId());
        assertEquals(X_COORDINATE, entity.getXCoordinate());
        assertEquals(Y_COORDINATE, entity.getYCoordinate());
    }

    @Test
    void testGetId() {
        assertEquals(ID, countryEntity.getId());
    }

    @Test
    void testGetName() {
        assertEquals(NAME, countryEntity.getName());
    }

    @Test
    void testGetContinentId() {
        assertEquals(CONTINENT_ID, countryEntity.getContinentId());
    }

    @Test
    void testGetXCoordinate() {
        assertEquals(X_COORDINATE, countryEntity.getXCoordinate());
    }

    @Test
    void testGetYCoordinate() {
        assertEquals(Y_COORDINATE, countryEntity.getYCoordinate());
    }

    @Test
    void testGetPlayerId() {
        assertEquals(null, countryEntity.getPlayerId());
    }

    @Test
    void testGetArmyCount() {
        assertEquals(0, countryEntity.getArmyCount());
    }

    @Test
    void testSetPlayerId() {
        var playerId = 1;
        countryEntity.setPlayerId(playerId);
        assertEquals(playerId, countryEntity.getPlayerId());
    }

    @Test
    void testIncrementArmyCount() {
        countryEntity.incrementArmyCount();
        assertEquals(1, countryEntity.getArmyCount());
    }

    @Test
    void testDecrementArmyCount() {
        countryEntity.decrementArmyCount();
        assertEquals(0, countryEntity.getArmyCount());
    }

    @Test
    void testCanAttackFrom() {
        countryEntity.incrementArmyCount();
        countryEntity.incrementArmyCount();
        assertTrue(countryEntity.canAttack());
    }
}
