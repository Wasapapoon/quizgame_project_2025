package item.usage;

public interface hasLife {

    int getHp();

    void setHp(int hp);

    void reduceHp();

    boolean isDead();
}