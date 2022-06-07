package Objects;

public class Weapon extends Item{
    private int damage;
    private String weaponType;

    public Weapon(Material material, String itemName, double value, double weight, String weaponType, int damage) {
        super(material, itemName,
                value,
                weight);
        this.weaponType = weaponType;
        setDamage(damage);
        setMaterial(material);
    }

    public void setDamage(int damage) {
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

    public int getDamage() {
        return damage;
    }

}
