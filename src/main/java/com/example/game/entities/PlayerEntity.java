package com.example.game.entities;

/**
 * The entity represents a player in the game.
 */
public class PlayerEntity {
    private final int id;
    private final String name;
    private int armyCount;
    private PlayerType type;

    public enum PlayerType {
        Human, AI,
    }

    /**
     * @param id the id of the player
     * @param name the name of the player
     * @param armyCount the army count of the player
     * @param type the type of the player
     */
    public PlayerEntity(int id, String name, int armyCount, PlayerType type) {
        this.id = id;
        this.name = name;
        this.armyCount = armyCount;
        this.type = type;
    }

    /**
     * @return the id of the player
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * @return the army count of the player
     */
    public int getArmyCount() {
        return armyCount;
    }

    /**
     * @return the type of the player
     */
    public PlayerType getType() {
        return type;
    }


    /**
     * Adds an army count to the player
     *
     * @param count the count to add
     */
    public void addArmyCount(int count) {
        armyCount += count;
    }
}
