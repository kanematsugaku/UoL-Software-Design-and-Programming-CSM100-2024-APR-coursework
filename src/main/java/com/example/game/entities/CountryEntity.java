package com.example.game.entities;

/**
 * This entity represents a country in the map.
 *
 * For more information see: https://domination.sourceforge.io/makemaps.shtml
 */
public class CountryEntity {
    public int id;
    public String name;
    public int continentId;
    public int xCoordinate;
    public int yCoordinate;

    public CountryEntity(int id, String name, int continentId, int xCoordinate, int yCoordinate) {
        this.id = id;
        this.name = name;
        this.continentId = continentId;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
}
