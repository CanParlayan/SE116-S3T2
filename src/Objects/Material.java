package Objects;

public class Material {
    private static int valueMultiplier;
    private static String name;
    private static double weight;
    private static int damageMultiplier;

    public static int getDamageMultiplier() {
        return damageMultiplier;
    }

    public static void setDamageMultiplier(int damageMultiplier) {
        Material.damageMultiplier = damageMultiplier;
    }

    public Material(int valueMultiplier, String name,int damageMultiplier) {
        Material.valueMultiplier = valueMultiplier;
        Material.name = name;
        Material.damageMultiplier = damageMultiplier;
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        Material.name = name;
    }

    public void setValueMultiplier(int valueMultiplier) {
        Material.valueMultiplier = valueMultiplier;
    }

    public static int getValueMultiplier() {
        return valueMultiplier;
    }

    public static double getWeight() {
        return weight;
    }

    public static void setWeight(double weight) {
        Material.weight = weight;
    }
}