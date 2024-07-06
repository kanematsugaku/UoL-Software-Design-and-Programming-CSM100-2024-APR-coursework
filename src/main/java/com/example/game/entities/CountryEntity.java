package com.example.game.entities;

/**
 * The entity represents a country in the map.
 *
 * For more information about fields see: https://domination.sourceforge.io/makemaps.shtml
 */
public class CountryEntity {
    private final int id;
    private final String name;
    private final int continentId;
    private final int xCoordinate;
    private final int yCoordinate;
    private Integer playerId;

    public CountryEntity(int id, String name, int continentId, int xCoordinate, int yCoordinate) {
        this.id = id;
        this.name = name;
        this.continentId = continentId;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.playerId = null;
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

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
