package HeroQuestArena;

class Warrior extends Hero {
    private int bonus;

    public Warrior(String name, int hp, int attackPower, int defenseLevel, int bonus) {
        super(name, "Warrior", hp, attackPower, defenseLevel, bonus);
        this.bonus = bonus;
    }

    public int getBonusPower() { return bonus; }
    public void setBonusPower(int bonus) { this.bonus = bonus; }


@Override
public int attack(Hero target) {
    System.out.println(getName() + " (Warrior) attacks " + target.getName() + " with a furious strike!");

    int damage = getAttackPower();

    // Rage bonus: The higher the rageLevel, the more bonus damage
    damage += getBonusPower();

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