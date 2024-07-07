package com.example.game.entities;

import java.util.Arrays;
import java.util.List;

/**
 * The entity represents a border between two countries in the map.
 *
 * For more information about fields @see: https://domination.sourceforge.io/makemaps.shtml
 */
public class BorderEntity {
    private final Integer countryId;
    private final List<Integer> adjacentCountryIds;

    /**
     * @param countryId the id of the country
     * @param adjacentCountryIds the ids of the countries adjacent to the country
     */
    public BorderEntity(Integer countryId, Integer... adjacentCountryIds) {
        this.countryId = countryId;
        this.adjacentCountryIds = Arrays.stream(adjacentCountryIds).toList();
    }

    /**
     * @return the id of the country
     */
    public Integer getCountryId() {
        return countryId;
    }

    /**
     * @return the ids of the countries adjacent to the country
     */
    public List<Integer> getAdjacentCountryIds() {
        return adjacentCountryIds;
    }
}
