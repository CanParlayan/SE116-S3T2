package Items;

import Objects.Armor;
import Objects.Material;


public class Padded extends Armor {
        public Padded(Material material) {
        super(material,
                Material.getName() + " Padded Armor",
                Material.getValueMultiplier() * 1.5,
                20.0,
                3);
    }
    }
