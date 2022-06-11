package Objects;

public class Armor extends Item{
    private int armorValue;
    private String armorType;

    public Armor(Material material, String itemName, double value, double weight, int armorValue) {
        super(material, itemName, value, weight);
        this.armorValue = armorValue;
    }


    public int getArmorValue() {
        return armorValue;
    }

    public void setArmorValue(int armorValue) {
        this.armorValue = armorValue;
    }

    public String getArmorType() {
        return armorType;
    }

}
