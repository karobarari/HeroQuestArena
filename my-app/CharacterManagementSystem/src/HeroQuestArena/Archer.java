package HeroQuestArena;

class Archer extends Hero {

    private int accuracy; // Archer's accuracy stat. Higher accuracy means higher chance of critical hits.

    /**
     * Constructor for the Archer class.
     *
     * @param name The name of the archer.
     * @param hp The initial hit points of the archer.
     * @param attackPower The attack power of the archer.
     * @param defenseLevel The defense level of the archer.
     * @param accuracy The accuracy of the archer, used for critical hit
     * calculations.
     */
    public Archer(String name, int hp, int attackPower, int defenseLevel, int accuracy) {
        // Call the constructor of the superclass (Hero) to initialize common attributes.
        super(name, "Archer", hp, attackPower, defenseLevel, accuracy);
        this.accuracy = accuracy;
    }

    /**
     * Gets the accuracy of the archer.
     *
     * @return The accuracy of the archer.
     */
    public int getAccuracy() {
        return accuracy;
    }

    /**
     * Sets the accuracy of the archer.
     *
     * @param accuracy The new accuracy value for the archer.
     */
    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * Overrides the attack method from the Hero class to implement the archer's
     * specific attack. Archers have a chance to deal critical damage based on
     * their accuracy.
     *
     * @param target The hero being attacked.
     * @return The amount of damage dealt to the target.
     */
    @Override
    public int attack(Hero target) {
        System.out.println(getName() + " (Archer) fires an arrow at " + target.getName() + "!");
        int damage = getAttackPower(); // Base damage is the archer's attackPower

        // Chance to deal critical damage based on accuracy
        if (Math.random() * 100 < getAccuracy()) {
            damage *= 2; // Critical Hit! Double the damage.
            System.out.println("Critical Hit!");
        }

        int newTargetHp = target.getHp() - damage; // Calculate the new HP of the target
        target.setHp(newTargetHp); // Update the target's HP
        System.out.println(target.getName() + " HP: " + target.getHp());
        return damage; // Return the amount of damage dealt
    }
}
