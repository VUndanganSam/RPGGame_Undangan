package com.mycompany.updated_tbg_game_undangan;

import java.util.Random;

public class Player extends Character {
    private String characterClass;

    public Player(String name, String characterClass) {
        super(name);
        this.characterClass = characterClass;
        initializeClassStats();
        setInitialHP();
    }

    private void initializeClassStats() {
        switch (characterClass.toLowerCase()) {
            case "mage":
                mAtk += 10;
                intelligence += 5;
                speed += 2;
                break;
            case "warrior":
                atk += 10;
                def += 5;
                speed += 1;
                break;
            case "tank":
                def += 10;
                luck += 5;
                maxHP += 50;
                currentHP = maxHP;
                break;
            case "alchemist":
                mDef += 5;
                intelligence += 10;
                speed += 3;
                break;
            default:
                System.out.println("Unknown class. Defaulting to Warrior stats.");
                atk += 10;
                def += 5;
                speed += 1;
                break;
        }
    }

    private void setInitialHP() {
        if (characterClass.equalsIgnoreCase("tank")) {
            // Already adjusted in initializeClassStats
        } else {
            maxHP = 100 + (level - 1) * 20;
            currentHP = maxHP;
        }
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public int calculateDamage() {
        int dmg = 0;
        switch (characterClass.toLowerCase()) {
            case "mage":
                dmg = mAtk + intelligence;
                break;
            case "warrior":
                dmg = atk;
                break;
            case "tank":
                dmg = atk / 2; // Tanks do less damage
                break;
            case "alchemist":
                dmg = mDef + intelligence;
                break;
            default:
                dmg = atk;
        }
        Random r = new Random();
        dmg += r.nextInt(5);
        return Math.max(dmg, 1);
    }

    public void attack(Monster monster) {
        int damage = calculateDamage() - monster.getDefense();
        if (damage < 1) damage = 1;
        System.out.println(name + " attacks " + monster.getName() + " for " + damage + " damage!");
        monster.takeDamage(damage);
    }
}
