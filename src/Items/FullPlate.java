package Items;

import Objects.Armor;
import Objects.Material;


public class FullPlate extends Armor {
    public FullPlate(Material material) {
        super(material,
                Material.getName() + " Full Plate Armor",
                Material.getValueMultiplier() * 9,
                50.0,
                7);
    }
}
