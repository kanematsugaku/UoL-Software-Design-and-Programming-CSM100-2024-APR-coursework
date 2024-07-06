package com.example.game.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.game.entities.PlayerEntity.PlayerType;

@SpringBootTest
class PlayerEntityTest {
    private PlayerEntity playerEntity;

    public static final int ID = 1;
    public static final String NAME = "Alice";
    public static final int ARMY_COUNT = 1;
    public static final PlayerType TYPE = PlayerType.Human;

    @BeforeEach
    void setUp() {
        playerEntity = new PlayerEntity(ID, NAME, ARMY_COUNT, TYPE);
    }

    @Test
    void testConstructor() {
        var entity = new PlayerEntity(ID, NAME, ARMY_COUNT, TYPE);

        assertEquals(ID, entity.getId());
        assertEquals(NAME, entity.getName());
        assertEquals(ARMY_COUNT, entity.getArmyCount());
        assertEquals(TYPE, entity.getType());
    }

    @Test
    void testGetId() {
        assertEquals(ID, playerEntity.getId());
    }

    @Test
    void testGetName() {
        assertEquals(NAME, playerEntity.getName());
    }

    @Test
    void testGetArmyCount() {
        assertEquals(ARMY_COUNT, playerEntity.getArmyCount());
    }

    @Test
    void testGetType() {
        assertEquals(TYPE, playerEntity.getType());
    }

    @Test
    void testAddArmyCount() {
        playerEntity.addArmyCount(1);
        assertEquals(ARMY_COUNT + 1, playerEntity.getArmyCount());
    }
}
