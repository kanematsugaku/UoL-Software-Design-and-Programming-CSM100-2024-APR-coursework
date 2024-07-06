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
    private int armyCount;

    /**
     * @param id the id of the country
     * @param name the name of the country
     * @param continentId the id of the continent the country is in
     * @param xCoordinate the x coordinate of the country
     * @param yCoordinate the y coordinate of the country
     */
    public CountryEntity(int id, String name, int continentId, int xCoordinate, int yCoordinate) {
        this.id = id;
        this.name = name;
        this.continentId = continentId;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.playerId = null;
        this.armyCount = 0;
    }

    /**
     * @return the id of the country
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name of the country
     */
    public String getName() {
        return name;
    }

    /**
     * @return the id of the continent the country is in
     */
    public int getContinentId() {
        return continentId;
    }

    /**
     * @return the x coordinate of the country
     */
    public int getXCoordinate() {
        return xCoordinate;
    }


    /**
     * @return the y coordinate of the country
     */
    public int getYCoordinate() {
        return yCoordinate;
    }

    /**
     * @return the id of the player who owns the country
     */
    public Integer getPlayerId() {
        return playerId;
    }

    /**
     * @return the army count of the country
     */
    public int getArmyCount() {
        return armyCount;
    }

    /**
     * @param playerId the id of the player who owns the country
     */
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    /**
     * Increments the army count by 1
     */
    public void incrementArmyCount() {
        this.armyCount++;
    }
}
