package Items;

import Objects.Armor;
import Objects.Material;


public class Padded extends Armor {
        public Padded(Material material,String itemName) {
        super(material,itemName,
                Material.getValueMultiplier() * 1.5,
                2,
                3);
    }
    }
