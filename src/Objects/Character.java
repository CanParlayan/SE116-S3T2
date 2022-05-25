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
            } else System.out.println("You can't carry any more!");
        }
        else System.out.println("That item isn't on the ground!");
    }

    public void Throw(Item item){
        if (this.inventory.contains(item)){
            this.inventory.remove(item);
            System.out.println("Threw " + item.getItemName() + " to the ground.");
        }
        else System.out.println("You don't have that in your inventory! If it's in your hand, try storing it.");
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
                this.strength = rng.nextInt(1, 6);
                this.vitality = rng.nextInt(6, 11);
                this.intelligence = rng.nextInt(1, 6);
                System.out.println("Tank created with STR: " + this.strength + " VIT: " + this.vitality + " INT: " + this.intelligence);
            }
            case "Fighter" -> {
                this.strength = rng.nextInt(6,11);
                this.vitality = rng.nextInt(3,8);
                this.intelligence = rng.nextInt(1,6);
                System.out.println("Fighter created with STR: " + this.strength + " VIT: " + this.vitality + " INT: " + this.intelligence);
            }
            case "Healer" -> {
                this.strength = rng.nextInt(3,8);
                this.vitality = rng.nextInt(1,6);
                this.intelligence = rng.nextInt(6,11);
                System.out.println("Healer created with STR: " + this.strength + " VIT: " + this.vitality + " INT: " + this.intelligence);
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
