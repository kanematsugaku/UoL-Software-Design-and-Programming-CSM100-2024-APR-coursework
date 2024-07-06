package com.example.game.entities;

/**
 * The entity represents a continent in the map.
 *
 * For more information about fields see: https://domination.sourceforge.io/makemaps.shtml
 */
public class ContinentEntity {
    private final int id;
    private final String name;
    private final int armyValue;
    private final String color;

    /**
     * @param id the id of the continent
     * @param name the name of the continent
     * @param armyValue the army value of the continent
     * @param color the color of the continent
     */
    public ContinentEntity(int id, String name, int armyValue, String color) {
        this.id = id;
        this.name = name;
        this.armyValue = armyValue;
        this.color = color;
    }

    /**
     * @return the id of the continent
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name of the continent
     */
    public String getName() {
        return name;
    }

    /**
     * @return the army value of the continent
     */
    public int getArmyValue() {
        return armyValue;
    }

    /**
     * @return the color of the continent
     */
    public String getColor() {
        return color;
    }
}
