package com.example.game.entities;

/**
 * The entity represents a continent in the map.
 *
 * For more information about fields @see: https://domination.sourceforge.io/makemaps.shtml
 */
public class ContinentEntity {
    private final Integer id;
    private final String name;
    private final Integer armyBonus;
    private final String color;

    /**
     * @param id the id of the continent
     * @param name the name of the continent
     * @param armyBonus the army bonus of the continent
     * @param color the color of the continent
     */
    public ContinentEntity(Integer id, String name, Integer armyBonus, String color) {
        this.id = id;
        this.name = name;
        this.armyBonus = armyBonus;
        this.color = color;
    }

    /**
     * @return the id of the continent
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the name of the continent
     */
    public String getName() {
        return name;
    }

    /**
     * @return the army bonus of the continent
     */
    public Integer getArmyBonus() {
        return armyBonus;
    }

    /**
     * @return the color of the continent
     */
    public String getColor() {
        return color;
    }
}
