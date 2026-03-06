package entity;

import item.usage.hasLife;

/**
 * The Player class represents a participant in the game.
 * It stores the player's identity and manages their health points (HP)
 * by implementing the {@link item.usage.hasLife} interface.
 */
public class Player implements hasLife {

    /** The name of the player. */
    private String name;

    /** The current health points of the player. */
    private int hp;

    /**
     * Constructs a new Player with a specified name and initializes HP to 3.
     * @param name The name to be assigned to the player.
     */
    public Player(String name) {
        this.name = name;
        this.hp = 3;
    }

    /**
     * Retrieves the name of the player.
     * @return The player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the name of the player.
     * @param name The new name for the player.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the current health points (HP) of the player.
     * @return The current HP value.
     */
    @Override
    public int getHp() {
        return hp;
    }

    /**
     * Sets the health points (HP) of the player to a specific value.
     * @param hp The HP value to set.
     */
    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Decreases the player's health points by one, provided the HP is greater than zero.
     */
    @Override
    public void reduceHp() {
        if (hp > 0) {
            hp--;
        }
    }

    /**
     * Checks if the player's health points have reached zero or less.
     * @return True if the player's HP is 0 or less, otherwise false.
     */
    @Override
    public boolean isDead() {
        return hp <= 0;
    }
}