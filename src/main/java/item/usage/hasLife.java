package item.usage;

/**
 * The hasLife interface defines the core life-management capabilities for entities.
 * It provides a standardized contract for objects that possess health points (HP),
 * allowing the game system to manage damage, recovery, and death states.
 */
public interface hasLife {

    /**
     * Retrieves the current health points of the entity.
     * @return The current amount of HP remaining.
     */
    int getHp();

    /**
     * Updates the health points of the entity to a specific value.
     * @param hp The new health point value to be assigned.
     */
    void setHp(int hp);

    /**
     * Decreases the health points of the entity, typically by a single unit.
     * This represents the entity taking damage or losing a life.
     */
    void reduceHp();

    /**
     * Evaluates whether the entity has exhausted its health points.
     * @return True if the HP is zero or less, otherwise false.
     */
    boolean isDead();
}