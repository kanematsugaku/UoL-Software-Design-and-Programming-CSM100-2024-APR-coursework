package com.example.game.entities;

/**
 * The entity represents a player in the game.
 */
public class PlayerEntity {
    private String name;
    private int armyCount;

    public PlayerEntity(String name, int armyCount) {
        this.name = name;
        this.armyCount = armyCount;
    }

    public String getName() {
        return name;
    }

    public int getArmyCount() {
        return armyCount;
    }
}
