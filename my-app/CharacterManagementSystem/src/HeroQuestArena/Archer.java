package HeroQuestArena;

class Archer extends Hero {
    private int accuracy;

    public Archer(String name, int hp, int attackPower, int defenseLevel, int accuracy) {
        super(name, "Archer", hp, attackPower, defenseLevel, accuracy);
        this.accuracy = accuracy;
    }

    public int getAccuracy() { return accuracy; }
    public void setAccuracy(int accuracy) { this.accuracy = accuracy; }


@Override
public int attack(Hero target) {
    System.out.println(getName() + " (Archer) fires an arrow at " + target.getName() + "!");
    int damage = getAttackPower();

    // Chance to deal critical damage based on accuracy
    if (Math.random() * 100 < getAccuracy()) {
        damage *= 2; // Critical Hit!
        System.out.println("Critical Hit!");
    }

    int newTargetHp = target.getHp() - damage;
    target.setHp(newTargetHp);
    System.out.println(target.getName() + " HP: " + target.getHp());
    return damage;
}
}