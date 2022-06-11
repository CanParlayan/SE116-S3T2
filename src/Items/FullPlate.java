package Items;

import Objects.Armor;
import Objects.Material;


public class FullPlate extends Armor {
    public FullPlate(Material material,String itemName) {
        super(material,
                itemName,
                Material.getValueMultiplier() * 9,
                5,
                7);
    }
}
