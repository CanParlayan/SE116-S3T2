package Objects;

public class Armor extends Item{
    private double armorValue;
    private String armorType;

    public Armor(Material material, String itemName, double value, double weight, double armorValue) {
        super(material, itemName, value, weight);
        this.armorValue = value;
    }

    public Armor() {
        super();
    }


    public double getArmorValue() {
        return armorValue;
    }

    public void setArmorValue(double armorValue) {
        this.armorValue = armorValue;
    }

    public String getArmorType() {
        return armorType;
    }

    public void setArmorType(String armorType) {
        this.armorType = armorType;
    }
}
