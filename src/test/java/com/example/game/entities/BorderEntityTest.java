package com.example.game.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BorderEntityTest {
    private BorderEntity borderEntity;

    private static final int COUNTRY_ID = 1;
    private static final int ADJACENT_COUNTRY_ID = 2;

    @BeforeEach
    void setUp() {
        borderEntity = new BorderEntity(COUNTRY_ID, ADJACENT_COUNTRY_ID);
    }

    @Test
    void testConstructorWithSingleAdjacentCountry() {
        var entity = new BorderEntity(COUNTRY_ID, ADJACENT_COUNTRY_ID);

        assertEquals(COUNTRY_ID, entity.getCountryId());
        assertEquals(ADJACENT_COUNTRY_ID, entity.getAdjacentCountryIds().get(0));
    }

    @Test
    void testConstructorWithMultipleAdjacentCountries() {
        var adjacentCountryId1 = 2;
        var adjacentCountryId2 = 3;
        var adjacentCountryId3 = 4;

        var entity = new BorderEntity(COUNTRY_ID, adjacentCountryId1, adjacentCountryId2,
                adjacentCountryId3);

        assertEquals(COUNTRY_ID, entity.getCountryId());
        assertEquals(adjacentCountryId1, entity.getAdjacentCountryIds().get(0));
        assertEquals(adjacentCountryId2, entity.getAdjacentCountryIds().get(1));
        assertEquals(adjacentCountryId3, entity.getAdjacentCountryIds().get(2));
    }

    @Test
    void testGetCountryId() {
        assertEquals(COUNTRY_ID, borderEntity.getCountryId());
    }

    @Test
    void testGetAdjacentCountryIds() {
        assertEquals(ADJACENT_COUNTRY_ID, borderEntity.getAdjacentCountryIds().get(0));
    }
}
