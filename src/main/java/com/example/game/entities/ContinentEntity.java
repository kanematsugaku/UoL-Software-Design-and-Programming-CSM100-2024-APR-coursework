package com.example.game.entities;

/**
 * The entity represents a continent in the map.
 *
 * For more information about fields see: https://domination.sourceforge.io/makemaps.shtml
 */
public class ContinentEntity {
    private int id;
    private String name;
    private int armyValue;
    private String color;

    public ContinentEntity(int id, String name, int armyValue, String color) {
        this.id = id;
        this.name = name;
        this.armyValue = armyValue;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getArmyValue() {
        return armyValue;
    }

    public String getColor() {
        return color;
    }
}
