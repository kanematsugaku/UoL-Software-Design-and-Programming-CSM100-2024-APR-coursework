package com.example.game.entities;

/**
 * The entity represents a border between two countries in the map.
 *
 * For more information about fields see: https://domination.sourceforge.io/makemaps.shtml
 */
public class BorderEntity {
    private final int countryId;
    private final int[] adjacentCountryIds;

    public BorderEntity(int countryId, int... adjacentCountryIds) {
        this.countryId = countryId;
        this.adjacentCountryIds = adjacentCountryIds;
    }

    public int getCountryId() {
        return countryId;
    }

    public int[] getAdjacentCountryIds() {
        return adjacentCountryIds;
    }
}
