package Objects;

public class Weapon extends Item{
    private double damage;
    private String weaponType;

    public Weapon(Material material,String itemName, double value, double weight,String weaponType,double damage) {
        super(material, itemName,
                value,
                weight);
        this.weaponType = weaponType;
        setDamage(damage);
        setMaterial(material);
    }

    public static double getDamage(double damage) {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
        }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }
    public void specialAction(){
    }

    public double getDamage() {
        return damage;
    }

}
