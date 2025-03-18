package HeroQuestArena;

public class Hero {

    private String name;
    private String classType;
    private int hp;
    private int attackPower;
    private int defenseLevel;
    private int special;
    private boolean isDefending = false;

    public Hero(String name, String classType, int hp, int attackPower, int defenseLevel,int special) {
        this.name = name;
        this.classType = classType;
        this.hp = hp;
        this.attackPower = attackPower;
        this.defenseLevel = defenseLevel;
        this.special = special;
    }

    public String getName() {
        return name;
    }

    public String getClassType() {
        return classType;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getDefenseLevel() {
        return defenseLevel;
    }

    public void setDefenseLevel(int defenseLevel) {
        this.defenseLevel = defenseLevel;
    }

 
public int attack(Hero target) {
    System.out.println(this.name + " attacks " + target.getName() + "!");
    int damage = this.attackPower;

    if(target.isDefending()){
        damage /= 2; // Reduce damage by 50% if defending
        System.out.println(target.getName() + " blocks!");
    }

    if(damage < 0){
        damage = 0;
    }

    int newTargetHp = target.getHp() - damage;
    target.setHp(newTargetHp);
    System.out.println(target.getName() + " HP: " + target.getHp());
    return damage;

}

    public void defend() {
        System.out.println(this.name + " is defending!");
        isDefending = true;
    }

    public void stopDefending() {
        isDefending = false;
    }

    public boolean isDefending() {
        return isDefending;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Class: " + classType + ", HP: " + hp
                + ", Attack: " + attackPower + ", Defense: " + defenseLevel;
    }
}
