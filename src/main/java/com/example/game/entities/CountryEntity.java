package com.example.game.entities;

/**
 * The entity represents a country in the map.
 *
 * For more information about fields see: https://domination.sourceforge.io/makemaps.shtml
 */
public class CountryEntity {
    private int id;
    private String name;
    private int continentId;
    private int xCoordinate;
    private int yCoordinate;

    public CountryEntity(int id, String name, int continentId, int xCoordinate, int yCoordinate) {
        this.id = id;
        this.name = name;
        this.continentId = continentId;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getContinentId() {
        return continentId;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }
}
