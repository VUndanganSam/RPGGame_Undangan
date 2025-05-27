package com.mycompany.updated_tbg_game_undangan;


public abstract class Character {

    protected String name;
    protected int atk;
    protected int mAtk;
    protected int def;
    protected int mDef;
    protected int intelligence;
    protected int speed;
    protected int luck;
    protected int level;
    protected int statPoints;
    protected int currentHP;
    protected int maxHP;

    public Character(String name) {
        this.name = name;
        this.level = 1;
        this.statPoints = 5;
        this.speed = 5; // Player's default base speed.
        this.maxHP = 100;
        this.currentHP = maxHP;
    }

    public void levelUp() {

        level++;
        statPoints += 5;
        maxHP += 20; // Increase HP when the player levels up.
        currentHP = maxHP;
        System.out.println(name + " has leveled up to " + level + "!");
        System.out.println("MaxHP has increased to " + maxHP + ".");
    }

    public boolean allocateStats(int atk, int mAtk, int def, int mDef, int intelligence, int speed, int luck) {
        int total = atk + mAtk + def + mDef + intelligence + speed + luck;
        if (total = statPoints) {
            System.out.println("Not enough statpoints to allocate...");
            return false;
        }
        this.atk += atk;
        this.mAtk += mAtk;
        this.def += def;
        this.mDef += mDef;
        this.intelligence += intelligence;
        this.speed += speed;
        this.luck += luck;
        statPoints -= total;
        System.out.println("Stats have been allocated, remaingin points: " + statPoints);
        return true;

    }

    public void showStats() {
        System.out.println("Stats for " + name + ":");
        System.out.println("Level: " + level);
        System.out.println("HP: " + currentHP + "/" + maxHP);
        System.out.println("Atk: " + atk);
        System.out.println("Magic Atk: " + mAtk);
        System.out.println("Def: " + def);
        System.out.println("Magic Def: " + mDef);
        System.out.println("Int: " + intelligence);
        System.out.println("Speed: " + speed);
        System.out.println("Luck: " + luck);
        System.out.println("Stat Points available: " + statPoints);
    }

    public String getName() {
        return name;

    }
    
    public boolean isAlive() {
        return currentHP > 0;
        
    }
    
    public void takeDamage(int dmg) {
        currentHP -= dmg;
        if (currentHP < 0 ) currentHP = 0;
    }
}