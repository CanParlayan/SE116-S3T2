package Objects;
import Items.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

//This class will probably be abstracted from a top class that also abstracts the enemy class
public class Character {
    //Small initialization note, decided to handle random generation inside this class and not expose it
    SecureRandom rng = new SecureRandom();
    private String charClass;
    private int strength;
    private List<Item> inventory = new ArrayList<>(); //no limitation because the limit is related to weight
    private Weapon heldWeapon;
    private Armor heldArmor;
    private int intelligence;
    private int vitality;
    private int health;
    private boolean isStunned;
    private int stunLength;
    private boolean isDead;
    private int specialCooldown;
    private Level gameLevel; //will be used to access the level drops without exposing it public, no setter/getters, but a constructor parameter.



    public void Pick(Item item) {
        if (this.gameLevel.getLevelDrops().contains(item)) {
            if (HasSpaceInInventory(item)) {
                this.inventory.add(item);
                System.out.println("Picked " + item.getItemName() + " from the ground.");
            }
            else System.out.println("You can't carry any more!");
        }
        else System.out.println("That item isn't on the ground!");
    }

    public void Throw(Item item){
        if (this.inventory.contains(item)){
            this.inventory.remove(item);
            this.gameLevel.getLevelDrops().add(item);
            System.out.println("Threw " + item.getItemName() + " to the ground.");
        }
        else System.out.println("You don't have that in your inventory! If it's in your hand, try storing it.");
    }

    public void Wield(Weapon weapon){ //probably a good idea to cover with exceptions
        //A logic operator could be used to simplify the checks here, however we want the program to bias towards
        //the inventory copy rather than the ground copy if same items exist both on the ground and the inventory.
        if (weapon.getWeight() < this.strength) {
            if (this.inventory.contains(weapon)) {
                if (!(heldWeapon == null)) {
                    inventory.remove(weapon);
                    if (HasSpaceInInventory(heldWeapon)) {
                        inventory.add(heldWeapon);
                        String oldWeaponName = heldWeapon.getItemName();
                        this.setHeldWeapon(weapon);
                        System.out.println("Grabbed " + weapon.getItemName() + " from the inventory and put " + oldWeaponName + " inside instead.");
                    } else {
                        String oldWeaponName = heldWeapon.getItemName();
                        this.gameLevel.AddToLevelDrops(heldWeapon);
                        this.setHeldWeapon(weapon);
                        System.out.println("Wielded " + weapon.getItemName() + " and threw " + oldWeaponName + " on the ground because there was no space in inventory.");
                    }
                } else {
                    inventory.remove(weapon);
                    this.setHeldWeapon(weapon);
                    System.out.println("Wielded " + weapon.getItemName() + " from the inventory.");
                }

            } else if (this.gameLevel.getLevelDrops().contains(weapon)) {
                if (!(heldWeapon == null)) {
                    this.gameLevel.RemoveFromLevelDrops(weapon);
                    if (HasSpaceInInventory(heldWeapon)) {
                        inventory.add(heldWeapon);
                        String oldWeaponName = heldWeapon.getItemName();
                        this.setHeldWeapon(weapon);
                        System.out.println("Grabbed " + weapon.getItemName() + " from the ground and put " + oldWeaponName + " in the inventory.");
                    } else {
                        String oldWeaponName = heldWeapon.getItemName();
                        this.gameLevel.AddToLevelDrops(heldWeapon);
                        this.setHeldWeapon(weapon);
                        System.out.println("Wielded " + weapon.getItemName() + " from the ground and threw " + oldWeaponName + " because there was no space in inventory.");
                    }
                } else {
                    this.gameLevel.RemoveFromLevelDrops(weapon);
                    this.setHeldWeapon(weapon);
                    System.out.println("Wielded " + weapon.getItemName() + " from the ground.");
                }
            }
        }
        else System.out.println("That thing is too heavy!");

    }

    public void Wear(Armor armor){ //probably a good idea to cover with exceptions
        //A logic operator could be used to simplify the checks here, however we want the program to bias towards
        //the inventory copy rather than the ground copy if same items exist both on the ground and the inventory.
        if (armor.getWeight() < this.strength) {
            if (this.inventory.contains(armor)) {
                if (!(heldArmor == null)) {
                    inventory.remove(armor);
                    if (HasSpaceInInventory(heldArmor)) {
                        inventory.add(heldArmor);
                        String oldArmorName = heldArmor.getItemName();
                        this.setHeldArmor(armor);
                        System.out.println("Grabbed " + armor.getItemName() + " from the inventory and put " + oldArmorName + " inside instead.");
                    } else {
                        String oldArmorName = heldArmor.getItemName();
                        this.gameLevel.AddToLevelDrops(heldArmor);
                        this.setHeldArmor(armor);
                        System.out.println("Wore " + armor.getItemName() + " and threw " + oldArmorName + " on the ground because there was no space in inventory.");
                    }
                } else {
                    inventory.remove(armor);
                    this.setHeldArmor(armor);
                    System.out.println("Wore " + armor.getItemName() + " from the inventory.");
                }

            } else if (this.gameLevel.getLevelDrops().contains(armor)) {
                if (!(heldArmor == null)) {
                    this.gameLevel.RemoveFromLevelDrops(armor);
                    if (HasSpaceInInventory(heldArmor)) {
                        inventory.add(heldArmor);
                        String oldArmorName = heldArmor.getItemName();
                        this.setHeldArmor(armor);
                        System.out.println("Grabbed " + armor.getItemName() + " from the ground and put " + oldArmorName + " in the inventory.");
                    } else {
                        String oldArmorName = heldArmor.getItemName();
                        this.gameLevel.AddToLevelDrops(heldArmor);
                        this.setHeldArmor(armor);
                        System.out.println("Wore " + armor.getItemName() + " from the ground and threw " + oldArmorName + " because there was no space in inventory.");
                    }
                } else {
                    this.gameLevel.RemoveFromLevelDrops(armor);
                    this.setHeldArmor(armor);
                    System.out.println("Wore " + armor.getItemName() + " from the ground.");
                }
            }
        }
        else System.out.println("That thing is too heavy!");

    }

    public boolean HasSpaceInInventory(Item item){ //weight values need to be adjusted
        double itemweight = item.getWeight();
        for (Item inventoryitem : this.inventory){
            itemweight += inventoryitem.getWeight();
        }
        return itemweight < this.strength * 15; //15 is arbitrary
    }

    public Character(String charClass, Level gamelevel) throws InvalidCharClassException {
        this.charClass = charClass;
        this.gameLevel = gamelevel;
        switch (charClass) { //simplified switch clause
            case "Tank" -> {
                this.strength = rng.nextInt(6)+1;
                this.vitality = rng.nextInt(6)+6;
                this.intelligence = rng.nextInt(6)+1;
                this.health = Math.toIntExact(Math.round(this.vitality * 0.7 + this.strength * 0.2 + this.intelligence * 0.1)); //required because Math.round returns long
                System.out.println("Tank created with STR: " + this.strength + " VIT: " + this.vitality + " INT: " + this.intelligence + " HP: " + this.health);
            }
            case "Fighter" -> {
                this.strength = rng.nextInt(6)+6;
                this.vitality = rng.nextInt(6)+3;
                this.intelligence = rng.nextInt(6)+1;
                this.health = Math.toIntExact(Math.round(this.vitality * 0.7 + this.strength * 0.2 + this.intelligence * 0.1));
                System.out.println("Fighter created with STR: " + this.strength + " VIT: " + this.vitality + " INT: " + this.intelligence+ " HP: " + this.health);
            }
            case "Healer" -> {
                this.strength = rng.nextInt(6)+3;
                this.vitality = rng.nextInt(6)+1;
                this.intelligence = rng.nextInt(6)+6;
                this.health = Math.toIntExact(Math.round(this.vitality * 0.7 + this.strength * 0.2 + this.intelligence * 0.1));
                System.out.println("Healer created with STR: " + this.strength + " VIT: " + this.vitality + " INT: " + this.intelligence + " HP: " + this.health);
            }
            case "Enemy" -> {
                this.strength = rng.nextInt(6)+1;
                this.vitality = rng.nextInt(6)+1;
                this.intelligence = rng.nextInt(6)+1;
                this.health = Math.toIntExact(Math.round(this.vitality * 0.7 + this.strength * 0.2 + this.intelligence * 0.1));
                //no output because this will be internal
            }
            default ->
                    throw new InvalidCharClassException(this.charClass); //very intelligently designed exception to catch invalid class names
        }
    }

    public int CalculateDamage(){
        if (heldWeapon instanceof Sword){
            return (this.heldWeapon.getDamage()*this.strength);
        }
        else if (heldWeapon instanceof Shield){
            return (this.heldWeapon.getDamage()*this.vitality);
        }
        else if (heldWeapon instanceof Wand){
            return (this.heldWeapon.getDamage()*this.intelligence);
        }
        else return this.strength; //unarmed attack
    }

    public void TakeDamage(int damage){
        if (heldArmor == null){
            System.out.println("ATTACK: " + this.getCharClass() + " takes " + damage + " damage.");
            this.setHealth(this.getHealth()-damage);
            System.out.println("They now have " + this.getHealth() + " HP.");
            this.CheckDead();
        }

        else if (heldArmor.getArmorValue() > 0){
            if (heldArmor.getArmorValue()-damage > 0){
                heldArmor.setArmorValue(heldArmor.getArmorValue()-damage);
                System.out.println("ATTACK: " + this.getCharClass() + " takes " + damage + " damage but all of it was blocked off by " + this.heldArmor.getItemName() + ".");
                System.out.println("New armor durability: " + heldArmor.getArmorValue());
                this.CheckDead();
            }
            else if (heldArmor.getArmorValue()-damage >= 0){
                int takenDamage = (int) Math.abs(this.heldArmor.getArmorValue() - damage);
                if(takenDamage == 0){
                    System.out.println("ATTACK: " + this.getCharClass() + " takes " + damage + " damage. Their " + heldArmor.getItemName() + " breaks while protecting them from the attack.");
                    this.heldArmor = null;
                    this.CheckDead();
                }
                else {
                    System.out.println("ATTACK: " + this.getCharClass() + " takes " + damage + " damage. Their " + heldArmor.getItemName() + "breaks.");
                    System.out.println("They take an extra " + takenDamage + " damage that the armor couldn't block.");
                    this.heldArmor = null;
                    this.setHealth(this.getHealth()-takenDamage);
                    System.out.println("Their HP is now " + this.getHealth());
                    this.CheckDead();
                }
            }

        }

    }
    public void CheckDead(){
        if (this.health <=0){
            System.out.println("Oh no! " + this.getCharClass() + " is dead!");
            this.isDead = true;
        }
}

    public String getCharClass() {
        return charClass;
    }

    public void setCharClass(String charClass) {
        this.charClass = charClass;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getVitality() {
        return vitality;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }

    public Weapon getHeldWeapon() {
        return heldWeapon;
    }

    public void setHeldWeapon(Weapon heldWeapon) {
        this.heldWeapon = heldWeapon;
    }

    public Armor getHeldArmor() {
        return heldArmor;
    }

    public void setHeldArmor(Armor heldArmor) {
        this.heldArmor = heldArmor;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setStunned(boolean stunned) {
        isStunned = stunned;
    }

    public void setStunLength(int stunLength) {
        this.stunLength = stunLength;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    public int getHealth() {
        return health;
    }

    public boolean isStunned() {
        return isStunned;
    }

    public int getStunLength() {
        return stunLength;
    }

    public boolean getIsDead() {
        return isDead;
    }

    public int getSpecialCooldown() {
        return specialCooldown;
    }

    public void setSpecialCooldown(int specialCooldown) {
        this.specialCooldown = specialCooldown;
    }
    public void DisplayStats(){
        System.out.println("Character class: " + this.charClass);
        System.out.println("Character strength: " + this.strength);
        System.out.println("Character vitality: " + this.vitality);
        System.out.println("Character intelligence: " + this.intelligence);
        System.out.println("Character health: " + this.health);
        if (this.heldArmor == null) System.out.println("Held armor: Unarmored");
        else {
            System.out.println("Held armor: " + this.heldArmor.getItemName());
            System.out.println("Armor material: " + this.heldArmor.getMaterial());
            System.out.println("Armor value: " + this.heldArmor.getArmorValue());
            System.out.println("Weight: " + this.heldArmor.getWeight());
        }
        if (this.heldWeapon == null) System.out.println("Held weapon: Unarmed");
        else {
            System.out.println("Held weapon: " + this.heldWeapon.getItemName());
            System.out.println("Weapon type: " + this.heldWeapon.getWeaponType());
            System.out.println("Weapon material: " + this.heldWeapon.getMaterial());
            System.out.println("Weapon damage: " + this.heldWeapon.getDamage());
        }
        //TBA: held/inventory
    }
}
