package com.mycompany.updated_tbg_game_undangan;

import java.util.Random;


public class Monster {
    private String name;
    private boolean isElite;
    private int maxHP;
    private int currentHP;
    private int atk;
    private int def;
    private int speed;
    private Random random;


    public Monster(String name, boolean isElite) {
        this.name = name;
        this.isElite = isElite;
        random = new Random();
        initializeStats();
    }

    private void initializeStats() {
        if (isElite) {
            maxHP = 150 + random.nextInt(50);
            atk = 15 + random.nextInt(10);
            def = 10 + random.nextInt(5);
            speed = 8 + random.nextInt(5);
        } else {
            maxHP = 50 + random.nextInt(51);
            atk = 5 + random.nextInt(6);
            def = 3 + random.nextInt(4);
            speed = 3 + random.nextInt(6);
        }
        currentHP = maxHP;
    }


    public String getName() {
        return name;
    }

    public boolean isElite() {
        return isElite;
    }


    public int getSpeed() {
        return speed;
    }

    public int getDefense() {
        return def;
    }

    public boolean isAlive() {
        return currentHP > 0;
    }


    public void takeDamage(int dmg) {
        currentHP -= dmg;
        if (currentHP < 0) currentHP = 0;
        System.out.println(name + " HP: " + currentHP + "/" + maxHP);
    }

    public void attack(Player player) {
        int damage = atk - player.def;
        if (damage < 1) damage = 1;
        System.out.println(name + " attacks " + player.getName() + " for " + damage + " damage!");
        player.takeDamage(damage);
    }
}
