package Items;

import Objects.Material;
import Objects.Weapon;

public class Shield extends Weapon {
        public Shield(Material material) {
            super(material,
                    Material.getName()+" Shield" ,
                    Material.getValueMultiplier()*3,
                    4,
                    "Vitality",
                    3*Material.getDamageMultiplier());
        }
}
