package Objects;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

//This class will probably be abstracted from a top class that also abstracts the enemy class
public class Character {
    //Small initialization note, decided to handle random generation inside this class and not expose it
    SecureRandom rng = new SecureRandom();
    private String charClass;
    private int strength;

    private List<Item> inventory = new ArrayList<Item>(); //no limitation because the limit is related to weight
    //to implement: dummy attack method, pick, wield, wear, etc...
    private Weapon heldWeapon;
    private Armor heldArmor;
    private int intelligence;
    private int vitality;
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

    public void Wield(Weapon weapon){
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
                System.out.println("Tank created with STR: " + this.strength + " VIT: " + this.vitality + " INT: " + this.intelligence);
            }
            case "Fighter" -> {
                this.strength = rng.nextInt(6)+6;
                this.vitality = rng.nextInt(6)+3;
                this.intelligence = rng.nextInt(6)+1;
                System.out.println("Fighter created with STR: " + this.strength + " VIT: " + this.vitality + " INT: " + this.intelligence);
            }
            case "Healer" -> {
                this.strength = rng.nextInt(6)+3;
                this.vitality = rng.nextInt(6)+1;
                this.intelligence = rng.nextInt(6)+6;
                System.out.println("Healer created with STR: " + this.strength + " VIT: " + this.vitality + " INT: " + this.intelligence);
            }
            case "Enemy" -> {
                this.strength = rng.nextInt(6)+1;
                this.vitality = rng.nextInt(6)+1;
                this.intelligence = rng.nextInt(6)+1;
                //no output because this will be internal
            }
            default ->
                    throw new InvalidCharClassException(this.charClass); //very intelligently designed exception to catch invalid class names
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


    public void DisplayStats(){
        System.out.println("Character class: " + this.charClass);
        System.out.println("Character strength: " + this.strength);
        System.out.println("Character vitality: " + this.vitality);
        System.out.println("Character intelligence: " + this.intelligence);
        //TBA: held/inventory
    }
}
