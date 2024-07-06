package com.example.game.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BorderEntityTest {

    @Test
    void testConstructorWithSingleAdjacentCountry() {
        var countryId = 1;
        var adjacentCountryId = 1;

        var entity = new BorderEntity(countryId, adjacentCountryId);

        assertEquals(countryId, entity.getCountryId());
        assertEquals(adjacentCountryId, entity.getAdjacentCountryIds()[0]);
    }

    @Test
    void testConstructorWithMultipleAdjacentCountries() {
        var countryId = 1;
        var adjacentCountryId1 = 2;
        var adjacentCountryId2 = 3;
        var adjacentCountryId3 = 4;

        var entity = new BorderEntity(countryId, adjacentCountryId1, adjacentCountryId2,
                adjacentCountryId3);

        assertEquals(countryId, entity.getCountryId());
        assertEquals(adjacentCountryId1, entity.getAdjacentCountryIds()[0]);
        assertEquals(adjacentCountryId2, entity.getAdjacentCountryIds()[1]);
        assertEquals(adjacentCountryId3, entity.getAdjacentCountryIds()[2]);
    }
}
