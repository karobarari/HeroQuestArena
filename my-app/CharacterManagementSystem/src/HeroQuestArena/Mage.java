package HeroQuestArena;

class Mage extends Hero {
    private int magicPower;

    public Mage(String name, int hp, int attackPower, int defenseLevel, int magicPower) {
        super(name, "Mage", hp, attackPower, defenseLevel, magicPower);
        this.magicPower = magicPower;
    }

    public int getMagicPower() { return magicPower; }
    public void setMagicPower(int magicPower) { this.magicPower = magicPower; }

    // Mage.java

@Override
public int attack(Hero target) {
    System.out.println(getName() + " (Mage) casts a magical bolt at " + target.getName() + "!");

    int damage = getAttackPower();

    // Magic Power scaling: Magic power amplifies the attack
    damage += getMagicPower() * 0.75;

    int newTargetHp = target.getHp() - damage;
    target.setHp(newTargetHp);
    System.out.println(target.getName() + " HP: " + target.getHp());
    return damage;
}

    @Override
    public void defend() {
    super.defend();
    System.out.println(getName() + " (Mage) puts up a magical shield!");
}
}