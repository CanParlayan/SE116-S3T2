package Items;

import Objects.Material;
import Objects.Weapon;

public class Shield extends Weapon {
        public Shield(Material material,String itemName) {
            super(material,
                    itemName ,
                    Material.getValueMultiplier()*3,
                    4,
                    "Vitality",
                    3*Material.getDamageMultiplier());
        }
}
