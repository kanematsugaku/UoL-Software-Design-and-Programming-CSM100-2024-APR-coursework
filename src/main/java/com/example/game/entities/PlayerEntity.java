package com.example.game.entities;

/**
 * The entity represents a player in the game.
 */
public class PlayerEntity {
    private final Integer id;
    private final String name;
    private Integer armyCount;
    private PlayerType type;

    public enum PlayerType {
        Human, Computer,
    }

    /**
     * @param id the id of the player
     * @param name the name of the player
     * @param armyCount the army count of the player
     * @param type the type of the player
     */
    public PlayerEntity(Integer id, String name, Integer armyCount, PlayerType type) {
        this.id = id;
        this.name = name;
        this.armyCount = armyCount;
        this.type = type;
    }

    /**
     * @return the id of the player
     */
    public Integer getId() {
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
    public Integer getArmyCount() {
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
    public void addArmyCount(Integer count) {
        armyCount += count;
    }

    /**
     * Decrements the army count by 1
     */
    public void decrementArmyCount() {
        armyCount--;
    }
}
