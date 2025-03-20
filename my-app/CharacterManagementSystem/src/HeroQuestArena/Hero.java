package HeroQuestArena;

public class Hero {

    private String name; // The name of the hero.
    private String classType; // The class of the hero (e.g., Warrior, Mage, Archer).
    private int hp; // The hit points of the hero.
    private int attackPower; // The attack power of the hero.
    private int defenseLevel; // The defense level of the hero.
    private int special; // A special stat that can be specific to each class.
    private boolean isDefending = false; // A flag to indicate if the hero is currently defending.

    /**
     * Constructor for the Hero class.
     *
     * @param name         The name of the hero.
     * @param classType    The class type of the hero.
     * @param hp           The initial hit points of the hero.
     * @param attackPower  The attack power of the hero.
     * @param defenseLevel The defense level of the hero.
     * @param special      The special stat of the hero.
     */
    public Hero(String name, String classType, int hp, int attackPower, int defenseLevel, int special) {
        this.name = name;
        this.classType = classType;
        this.hp = hp;
        this.attackPower = attackPower;
        this.defenseLevel = defenseLevel;
        this.special = special;
    }

    /**
     * Gets the name of the hero.
     *
     * @return The name of the hero.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the class type of the hero.
     *
     * @return The class type of the hero.
     */
    public String getClassType() {
        return classType;
    }

    /**
     * Gets the hit points of the hero.
     *
     * @return The hit points of the hero.
     */
    public int getHp() {
        return hp;
    }

    /**
     * Sets the hit points of the hero.
     *
     * @param hp The new hit points value.
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Gets the attack power of the hero.
     *
     * @return The attack power of the hero.
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * Sets the attack power of the hero.
     *
     * @param attackPower The new attack power value.
     */
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    /**
     * Gets the defense level of the hero.
     *
     * @return The defense level of the hero.
     */
    public int getDefenseLevel() {
        return defenseLevel;
    }

    /**
     * Sets the defense level of the hero.
     *
     * @param defenseLevel The new defense level value.
     */
    public void setDefenseLevel(int defenseLevel) {
        this.defenseLevel = defenseLevel;
    }


    /**
     * Simulates an attack on a target hero.
     *
     * @param target The hero being attacked.
     * @return The amount of damage dealt to the target.
     */
    public int attack(Hero target) {
        System.out.println(this.name + " attacks " + target.getName() + "!");
        int damage = this.attackPower; // Base damage is the attacker's attackPower

        // If the target is defending, reduce the damage by 50%
        if (target.isDefending()) {
            damage /= 2; // Reduce damage by 50% if defending
            System.out.println(target.getName() + " blocks!");
        }

        // Ensure damage is not negative
        if (damage < 0) {
            damage = 0;
        }

        int newTargetHp = target.getHp() - damage; // Calculate the new HP of the target
        target.setHp(newTargetHp); // Update the target's HP
        System.out.println(target.getName() + " HP: " + target.getHp());
        return damage; // Return the amount of damage dealt

    }

    /**
     * Sets the hero to a defending state, reducing incoming damage.
     */
    public void defend() {
        System.out.println(this.name + " is defending!");
        isDefending = true;
    }

    /**
     * Removes the defending state from the hero.
     */
    public void stopDefending() {
        isDefending = false;
    }

    /**
     * Checks if the hero is currently defending.
     *
     * @return True if the hero is defending, false otherwise.
     */
    public boolean isDefending() {
        return isDefending;
    }

    /**
     * Returns a string representation of the Hero object.
     *
     * @return A string containing the hero's name, class, HP, attack, and defense.
     */
    @Override
    public String toString() {
        return "Name: " + name + ", Class: " + classType + ", HP: " + hp
                + ", Attack: " + attackPower + ", Defense: " + defenseLevel;
    }
}