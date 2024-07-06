package com.example.game.entities;

/**
 * The entity represents a player in the game.
 */
public class PlayerEntity {
    private final int id;
    private final String name;
    private int armyCount;

    public PlayerEntity(int id, String name, int armyCount) {
        this.id = id;
        this.name = name;
        this.armyCount = armyCount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getArmyCount() {
        return armyCount;
    }
}
