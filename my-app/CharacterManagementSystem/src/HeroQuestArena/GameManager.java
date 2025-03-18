package HeroQuestArena;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager {

    private ArrayList<Hero> heroes;

    public GameManager() {
        this.heroes = new ArrayList<>();
    }

    public Hero createHero(String name, String classType, int hp, int attackPower, int defenseLevel, int special) {
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

    public void addHero(Hero hero) {
        if (hero.getName() == null || hero.getName().trim().isEmpty()) {
            System.out.println("Error: Hero name cannot be empty.");
            return; // Don't add the hero
        }

        if (getHero(hero.getName()) != null) {
            System.out.println("Error: Hero with name '" + hero.getName() + "' already exists.");
            return; // Or throw an exception
        }

        heroes.add(hero);
        System.out.println(hero.getName() + " added to the game.");

    }

    public Hero getHero(String name) {
        for (Hero hero : heroes) {
            if (hero.getName().equals(name)) {
                return hero;
            }
        }
        return null; // Hero not found
    }

    public List<Hero> getAllHeroes() {
        return heroes;
    }

    public String simulateBattle(Hero hero1, Hero hero2) {
        if (hero1 == null || hero2 == null) {
            return "Error: Invalid heroes for battle.";
        }

        StringBuilder battleLog = new StringBuilder();
        battleLog.append("Battle between ").append(hero1.getName()).append(" (").append(hero1.getClass().getSimpleName()).append(")")
                .append(" and ").append(hero2.getName()).append(" (").append(hero2.getClass().getSimpleName()).append(")").append("\n");

        Random random = new Random();  // adding some unpredictability

        int round = 1;
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
                break;
            }

            // Hero 2 attacks
            int damage2 = hero2.attack(hero1);  // Get damage dealt
            battleLog.append(hero2.getName()).append(" attacks ").append(hero1.getName()).append(" for ").append(damage2).append(" damage.\n");
            if (hero1.isDefending()) {
                battleLog.append(hero1.getName()).append(" blocks some of the damage!\n");
            }
            battleLog.append(hero1.getName()).append(" HP: ").append(hero1.getHp()).append("\n");
            if (hero1.getHp() <= 0) {
                break;
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
        if (hero1.getHp() <= 0) {
            battleLog.append(hero2.getName()).append(" wins!");
        } else {
            battleLog.append(hero1.getName()).append(" wins!");
        }

        return battleLog.toString();
    }

    public void upgradeHeroStats(Hero hero, String statToUpgrade, int amount) {
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

    private int getStatValue(Hero hero, String stat) {
        return switch (stat.toLowerCase()) {
            case "hp" ->
                hero.getHp();
            case "attackpower" ->
                hero.getAttackPower();
            case "defenselevel" ->
                hero.getDefenseLevel();
            default ->
                -1;
        }; // Indicate error
    }
}
