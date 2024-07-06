package com.example.game.entities;

/**
 * The entity represents a player in the game.
 */
public class PlayerEntity {
    public String name;
    public int armyCount;

    public PlayerEntity(String name, int armyCount) {
        this.name = name;
        this.armyCount = armyCount;
    }
}
