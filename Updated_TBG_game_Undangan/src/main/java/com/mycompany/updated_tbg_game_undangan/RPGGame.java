package com.mycompany.updated_tbg_game_undangan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RPGGame {

    private Player player;
    private List<Monster> monsters;
    private List<Monster> eliteMonsters;
    private Random random;
    private Scanner scanner;

    public RPGGame() {
        scanner = new Scanner(System.in);
        random = new Random();
        monsters = new ArrayList<>();
        populateMonsters();
    }

    private void populateMonsters() {
        String[] basicNames = {"Slime", "Orc", "Goblin", "Centaur", "Skeleton", "Mimic", "Wild Wolf", "Wild Boar"};
        for (String name : basicNames) {
            monsters.add(new Monster(name, false));
        }
        eliteMonsters.add(new Monster("Demon King", true));
        eliteMonsters.add(new Monster("Dark Sorcerer", true));
        eliteMonsters.add(new Monster("Shadow Beast", true));
        eliteMonsters.add(new Monster("Corrupted Ancient", true));
    }

    public void start() {
        welcomeScreen();
        classPicker();
        introduceStory();
        startGameLoop();
    }

    private void welcomeScreen() {
        System.out.println("Welcome to the RPG game!");
        System.out.println("Please enter your username:");
        String username = scanner.nextLine();
        player = new Player(username, "");
    }

    private void classPicker() {
        System.out.println("Please choose your class:");
        while (true) {
            System.out.println("Enter your class: ");
            String chosenClass = scanner.nextLine();
            if (chosenClass.equalsIgnoreCase("Mage") || chosenClass.equalsIgnoreCase("Warrior") || chosenClass.equalsIgnoreCase("Tank") || chosenClass.equalsIgnoreCase("Alchemist")) {
                player = new Player(player.getName(), chosenClass);
                System.out.println("Class chosen: " + chosenClass);
                break;
            } else {
                System.out.println("Invalid class, please try again...");
            }
        }
        player.showStats();
    }

    private void introduceStory() {
        System.out.println("\n" + player.getName() + ", you died in an accident during a car crash in the city back on Earth, but you have been revived and have been transported to a medieval-ish realm.");
        System.out.println("You are tasked to defeat the four elite monsters of the Demon King.\n");
    }

    private void startGameLoop() {
        boolean playing = true;
        while (playing) {
            System.out.println("\nWhat would you like to do? (explore, allocate, stats, quit): ");
            String action = scanner.nextLine().trim().toLowerCase();
            switch (action) {
                case "explore":
                    explore();
                    if (!player.isAlive()) {
                        System.out.println("You have been defeated! Game over...");
                        playing = false;
                    }
                    break;
                case "allocate":
                    allocateStats();
                    break;
                case "stats":
                    player.showStats();
                    break;
                case "quit":
                    playing = false;
                    System.out.println("Thank you for playing!");
                    break;
                default:
                    System.out.println("Invalid option, try again...");
            }
        }
    }

    private void explore() {
        int encounterRoll = random.nextInt(100) + 1;
        if (encounterRoll <= 45) {
            Monster monster = generateMonster(false);
            System.out.println("You encountered a " + monster.getName() + "!");
            battle(monster);
        } else if (encounterRoll <= 55) {
            Monster elite = generateMonster(true);
            System.out.println("Elite Monster Encounter! You face the " + elite.getName() + "!");
            battle(elite);
        } else {
            System.out.println("There are no enemies here, Safe for now...");
        }
    }

    private Monster generateMonster(boolean elite) {
        if (elite) {
            int idx = random.nextInt(eliteMonsters.size());
            return new Monster(eliteMonsters.get(idx).getName(), true);
        } else {
            int idx = random.nextInt(monsters.size());
            return new Monster(monsters.get(idx).getName(), false);
        }
    }

    private void battle(Monster monster) {
        System.out.println("\nBattle Start! " + player.getName() + " vs " + monster.getName());
        System.out.println(player.getName() + " HP: " + player.currentHP + "/" + player.maxHP);
        System.out.println(monster.getName() + " HP: " + monster.currentHP + "/" + monster.maxHP);

        while (player.isAlive() && monster.isAlive()) {
            playerTurn(monster);
            if (monster.isAlive()) {
                monsterTurn(monster);
            } else {
                mosnterTurn(monster);
                if(player.isAlive()) {
                    playerTirn(monster);
                }
            }
        }
        
        if (player.isAlive()) {
    }

    private void allocateStats() {

    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
