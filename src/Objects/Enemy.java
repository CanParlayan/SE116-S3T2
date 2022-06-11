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

    void Attack(Character character){
        character.setHealth(character.getHealth()-getStrength());
    }

    Item dropRandomItem(){
        int randomizer = random.nextInt(3);
        Weapon lootWeapon = new Weapon(null,null,0,0,null,0);
        if(randomizer == 0){
            Material lootMaterial = new Material(random.nextInt(5),"steel",random.nextInt(5));
            Weapon temp = new Weapon(lootMaterial,"Forgotten weapon",10,3,"sword",10);
            lootWeapon = temp;
        }
        else if(randomizer == 1){
            Material lootMaterial2 = new Material(random.nextInt(6),"crystal",random.nextInt(6));
            Weapon temp2 = new Weapon(lootMaterial2,"Forbidden weapon",12,5,"sword",12);
            lootWeapon = temp2;
        }
        else if(randomizer == 2){
            Material lootMaterial3 = new Material(random.nextInt(7),"mithril",random.nextInt(7));
            Weapon temp3 = new Weapon(lootMaterial3,"Forsaken weapon",14,7,"sword",14);
            lootWeapon = temp3;
        }
        return lootWeapon;
    }

    void printInfo(){
        System.out.println("vitality:"+getVitality());
        System.out.println("strength:"+getStrength());
        System.out.println("intelligence:"+getIntelligence());
        System.out.println("hp:"+getHp());
        System.out.println("---------------");
    }

}
