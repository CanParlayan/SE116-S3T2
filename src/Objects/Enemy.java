package Objects;

import java.security.SecureRandom;

public class Enemy extends Character{
    private String name;
    private int strength;
    private int vitality;
    private int intelligence;
    private int hp = (int)(0.7*vitality+0.2*strength+0.1*intelligence);

    SecureRandom random = new SecureRandom();
    static Level level = new Level();
    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }

    public int getVitality() {
        return vitality;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public Enemy(int strength, int vitality, int intelligence,int hp,Level gamelevel,Weapon weapon,Armor armor){
        super("Enemy",gamelevel,weapon,armor);
        this.strength = strength;
        this.vitality = vitality;
        this.intelligence = intelligence;
        this.hp = hp;
     //   System.out.println("Enemy created with STR: " + this.strength + " VIT: " + this.vitality + " INT: " + this.intelligence + " HP: " + this.hp);
    }

}
