package entity;

import item.usage.hasLife;

public class Player implements hasLife {
    private String name;
    private int hp;

    public Player(String name) {
        this.name = name;
        this.hp = 3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public void reduceHp() {
        if (hp > 0) {
            hp--;
        }
    }

    @Override
    public boolean isDead() {
        return hp <= 0;
    }
}