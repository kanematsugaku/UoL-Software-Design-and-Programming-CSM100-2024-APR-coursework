package com.example.game.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlayerEntityTest {
    private PlayerEntity playerEntity;

    public static final String NAME = "Alice";
    public static final int ARMY_COUNT = 1;

    @BeforeEach
    void setUp() {
        playerEntity = new PlayerEntity(NAME, ARMY_COUNT);
    }

    @Test
    void testConstructor() {
        var entity = new PlayerEntity(NAME, ARMY_COUNT);

        assertEquals(NAME, entity.getName());
        assertEquals(ARMY_COUNT, entity.getArmyCount());
    }

    @Test
    void testGetName() {
        assertEquals(NAME, playerEntity.getName());
    }

    @Test
    void testGetArmyCount() {
        assertEquals(ARMY_COUNT, playerEntity.getArmyCount());
    }
}
