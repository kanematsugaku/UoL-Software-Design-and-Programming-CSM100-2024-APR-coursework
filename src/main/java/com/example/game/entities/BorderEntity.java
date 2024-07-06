package com.example.game.entities;

/**
 * The entity represents a border between two countries in the map.
 *
 * For more information about fields see: https://domination.sourceforge.io/makemaps.shtml
 */
public class BorderEntity {
    private final int countryId;
    private final int[] adjacentCountryIds;

    /**
     * @param countryId the id of the country
     * @param adjacentCountryIds the ids of the countries adjacent to the country
     */
    public BorderEntity(int countryId, int... adjacentCountryIds) {
        this.countryId = countryId;
        this.adjacentCountryIds = adjacentCountryIds;
    }

    /**
     * @return the id of the country
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * @return the ids of the countries adjacent to the country
     */
    public int[] getAdjacentCountryIds() {
        return adjacentCountryIds;
    }
}
