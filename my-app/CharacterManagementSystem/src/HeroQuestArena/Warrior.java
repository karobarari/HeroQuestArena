package HeroQuestArena;

class Warrior extends Hero {
    private int rageLevel;

    public Warrior(String name, int hp, int attackPower, int defenseLevel, int rageLevel) {
        super(name, "Warrior", hp, attackPower, defenseLevel, rageLevel);
        this.rageLevel = rageLevel;
    }

    public int getRageLevel() { return rageLevel; }
    public void setRageLevel(int rageLevel) { this.rageLevel = rageLevel; }


@Override
public int attack(Hero target) {
    System.out.println(getName() + " (Warrior) attacks " + target.getName() + " with a furious strike!");

    int damage = getAttackPower();

    // Rage bonus: The higher the rageLevel, the more bonus damage
    damage += getRageLevel();

    int newTargetHp = target.getHp() - damage;
    target.setHp(newTargetHp);
    System.out.println(target.getName() + " HP: " + target.getHp());
    return damage;
}

    @Override
    public void defend() {
    super.defend(); // Call the base Hero's defend() first
    System.out.println(getName() + " (Warrior) braces for impact!");
}
}