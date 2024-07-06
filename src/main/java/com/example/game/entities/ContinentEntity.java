package com.example.game.entities;

/**
 * The entity represents a continent in the map.
 *
 * For more information about fields see: https://domination.sourceforge.io/makemaps.shtml
 */
public class ContinentEntity {
    public int id;
    public String name;
    public int armyValue;
    public String color;

    public ContinentEntity(int id, String name, int armyValue, String color) {
        this.id = id;
        this.name = name;
        this.armyValue = armyValue;
        this.color = color;
    }
}
