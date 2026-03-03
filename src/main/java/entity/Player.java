package entity;

import item.usage.hasLife;

public class Player implements hasLife {
    private String name;
    private int score;
    private int hp;

    public Player(String name) {
        this.name = name;
        this.hp = 3;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int points) {
        this.score += points;
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