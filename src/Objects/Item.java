package Objects;

public abstract class Item {
    private Material material;
    private String itemName;
    private double value;
    private double weight;

    public Item(Material material, String itemName, double value, double weight) {
        this.material = material;
        this.itemName = itemName;
        this.value = value;
        this.weight = weight;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}