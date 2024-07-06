package com.example.game.entities;

/**
 * The entity represents a border between two countries in the map.
 *
 * For more information about fields see: https://domination.sourceforge.io/makemaps.shtml
 */
public class BorderEntity {
    public int countryId;
    public int[] adjacentCountryIds;

    public BorderEntity(int countryId, int... adjacentCountryIds) {
        this.countryId = countryId;
        this.adjacentCountryIds = adjacentCountryIds;
    }
}
