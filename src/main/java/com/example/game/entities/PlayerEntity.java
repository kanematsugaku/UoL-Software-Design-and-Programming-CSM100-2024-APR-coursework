package com.example.game.entities;

/**
 * The entity represents a player in the game.
 */
public class PlayerEntity {
    private final int id;
    private final String name;
    private int armyCount;
    private PlayerType type;

    public PlayerEntity(int id, String name, int armyCount, PlayerType type) {
        this.id = id;
        this.name = name;
        this.armyCount = armyCount;
        this.type = type;
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

    public PlayerType getType() {
        return type;
    }

    public enum PlayerType {
        Human, AI,
    }
}
