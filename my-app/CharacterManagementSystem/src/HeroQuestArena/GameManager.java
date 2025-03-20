package HeroQuestArena;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager {

    // List to hold all the heroes in the game. Using ArrayList for dynamic resizing.
    private ArrayList<Hero> heroes;

    // Constructor to initialize the GameManager.
    public GameManager() {
        this.heroes = new ArrayList<>(); // Initialize the heroes list.
    }

    /**
     * Creates a new hero object based on the provided class type.
     *
     * @param name         The name of the hero.
     * @param classType    The class type of the hero (Warrior, Mage, Archer).
     * @param hp           The initial hit points of the hero.
     * @param attackPower  The attack power of the hero.
     * @param defenseLevel The defense level of the hero.
     * @param special      A special stat specific to each class.
     * @return The newly created Hero object, or a generic Hero if the class type is invalid.
     */
    public Hero createHero(String name, String classType, int hp, int attackPower, int defenseLevel, int special) {
        // Use a switch statement to create a specific Hero subclass based on classType.
        switch (classType.toLowerCase()) {
            case "warrior" -> {
                return new Warrior(name, hp, attackPower, defenseLevel, special); //Special here would be rageLevel
            }
            case "mage" -> {
                return new Mage(name, hp, attackPower, defenseLevel, special); //Special here would be magicPower
            }
            case "archer" -> {
                return new Archer(name, hp, attackPower, defenseLevel, special); //Special here would be accuracy
            }
            default -> {
                System.out.println("Invalid class type. Creating a generic Hero.");
                return new Hero(name, classType, hp, attackPower, defenseLevel, special);
            }
        }
    }

    /**
     * Adds a hero to the game.  Performs validation to prevent adding heroes with empty names
     * or duplicate names.
     *
     * @param hero The hero to be added.
     */
    public void addHero(Hero hero) {
        // Input validation: Check if the hero's name is null or empty.
        if (hero.getName() == null || hero.getName().trim().isEmpty()) {
            System.out.println("Error: Hero name cannot be empty.");
            return; // Don't add the hero
        }

        // Check if a hero with the same name already exists
        if (getHero(hero.getName()) != null) {
            System.out.println("Error: Hero with name '" + hero.getName() + "' already exists.");
            return; // Or throw an exception
        }

        heroes.add(hero); // Add the hero to the list of heroes.
        System.out.println(hero.getName() + " added to the game.");

    }

    /**
     * Retrieves a hero from the game by their name.
     *
     * @param name The name of the hero to retrieve.
     * @return The Hero object if found, otherwise null.
     */
    public Hero getHero(String name) {
        // Iterate through the list of heroes.
        for (Hero hero : heroes) {
            // Check if the current hero's name matches the given name.
            if (hero.getName().equals(name)) {
                return hero; // Return the hero if found.
            }
        }
        return null; // Hero not found
    }

    /**
     * Retrieves a list of all heroes in the game.
     *
     * @return A List containing all Hero objects.
     */
    public List<Hero> getAllHeroes() {
        return heroes;
    }

    /**
     * Simulates a battle between two heroes.
     *
     * @param hero1 The first hero.
     * @param hero2 The second hero.
     * @return A string containing the battle log.
     */
    public String simulateBattle(Hero hero1, Hero hero2) {
        // Error handling: Check if either hero is null.
        if (hero1 == null || hero2 == null) {
            return "Error: Invalid heroes for battle.";
        }

        StringBuilder battleLog = new StringBuilder(); // Use StringBuilder for efficient string concatenation.
        battleLog.append("Battle between ").append(hero1.getName()).append(" (").append(hero1.getClass().getSimpleName()).append(")")
                .append(" and ").append(hero2.getName()).append(" (").append(hero2.getClass().getSimpleName()).append(")").append("\n");

        Random random = new Random();  // adding some unpredictability

        int round = 1;
        // Continue the battle as long as both heroes are alive (HP > 0).
        while (hero1.getHp() > 0 && hero2.getHp() > 0) {
            battleLog.append("\n--- Round ").append(round++).append(" ---\n");

            // Hero 1 attacks
            int damage1 = hero1.attack(hero2);  // Get damage dealt
            battleLog.append(hero1.getName()).append(" attacks ").append(hero2.getName()).append(" for ").append(damage1).append(" damage.\n");
            if (hero2.isDefending()) {
                battleLog.append(hero2.getName()).append(" blocks some of the damage!\n");
            }
            battleLog.append(hero2.getName()).append(" HP: ").append(hero2.getHp()).append("\n");
            if (hero2.getHp() <= 0) {
                break; // Hero 2 is defeated.
            }

            // Hero 2 attacks
            int damage2 = hero2.attack(hero1);  // Get damage dealt
            battleLog.append(hero2.getName()).append(" attacks ").append(hero1.getName()).append(" for ").append(damage2).append(" damage.\n");
            if (hero1.isDefending()) {
                battleLog.append(hero1.getName()).append(" blocks some of the damage!\n");
            }
            battleLog.append(hero1.getName()).append(" HP: ").append(hero1.getHp()).append("\n");
            if (hero1.getHp() <= 0) {
                break; // Hero 1 is defeated.
            }

            // Add some flavor text
            if (random.nextDouble() < 0.2) { // 20% chance of flavor text
                battleLog.append("The crowd roars!\n");
            }

            //Hero logic for defending randomly
            if (random.nextDouble() < 0.3) {
                hero1.defend();
            } else {
                hero1.stopDefending();
            }

            if (random.nextDouble() < 0.3) {
                hero2.defend();
            } else {
                hero2.stopDefending();
            }

        }

        battleLog.append("\n--- Battle End ---\n");
        // Determine the winner based on which hero's HP is zero or less.
        if (hero1.getHp() <= 0) {
            battleLog.append(hero2.getName()).append(" wins!");
        } else {
            battleLog.append(hero1.getName()).append(" wins!");
        }

        return battleLog.toString(); // Return the complete battle log.
    }

    /**
     * Upgrades a hero's stats.
     *
     * @param hero         The hero to upgrade.
     * @param statToUpgrade The stat to upgrade (hp, attackPower, defenseLevel).
     * @param amount       The amount to increase the stat by.
     */
    public void upgradeHeroStats(Hero hero, String statToUpgrade, int amount) {
        // Use a switch statement to determine which stat to upgrade.
        switch (statToUpgrade.toLowerCase()) {
            case "hp" ->
                hero.setHp(hero.getHp() + amount);
            case "attackpower" ->
                hero.setAttackPower(hero.getAttackPower() + amount);
            case "defenselevel" ->
                hero.setDefenseLevel(hero.getDefenseLevel() + amount);
            default ->
                System.out.println("Invalid stat to upgrade.");
        }
        System.out.println(hero.getName() + "'s " + statToUpgrade + " increased by " + amount + ". New value: " + getStatValue(hero, statToUpgrade));
    }

    /**
     * Helper method to get the value of a specific stat for a hero.
     *
     * @param hero The hero object.
     * @param stat The stat to retrieve (hp, attackpower, defenselevel).
     * @return The value of the stat, or -1 if the stat is invalid.
     */
    private int getStatValue(Hero hero, String stat) {
        return switch (stat.toLowerCase()) {
            case "hp" ->
                hero.getHp();
            case "attackpower" ->
                hero.getAttackPower();
            case "defenselevel" ->
                hero.getDefenseLevel();
            default ->
                -1; // Indicate error
        };
    }
}