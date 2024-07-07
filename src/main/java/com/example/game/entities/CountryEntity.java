package com.example.game.entities;

/**
 * The entity represents a country in the map.
 *
 * For more information about fields @see: https://domination.sourceforge.io/makemaps.shtml
 */
public class CountryEntity {
    private final Integer id;
    private final String name;
    private final Integer continentId;
    private final Integer xCoordinate;
    private final Integer yCoordinate;
    private Integer playerId;
    private Integer armyCount;

    /**
     * @param id the id of the country
     * @param name the name of the country
     * @param continentId the id of the continent the country is in
     * @param xCoordinate the x coordinate of the country
     * @param yCoordinate the y coordinate of the country
     */
    public CountryEntity(Integer id, String name, Integer continentId, Integer xCoordinate,
            Integer yCoordinate) {
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
    public Integer getId() {
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
    public Integer getContinentId() {
        return continentId;
    }

    /**
     * @return the x coordinate of the country
     */
    public Integer getXCoordinate() {
        return xCoordinate;
    }


    /**
     * @return the y coordinate of the country
     */
    public Integer getYCoordinate() {
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
    public Integer getArmyCount() {
        return armyCount;
    }

    /**
     * @param playerId the id of the player who owns the country
     */
    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    /**
     * Increments the army count by 1
     */
    public void incrementArmyCount() {
        this.armyCount++;
    }

    /**
     * Checks if the country can attack from
     *
     * @return true if the country can attack from
     */
    public boolean canAttackFrom() {
        return this.armyCount >= 2;
    }
}
