package Items;

import Objects.Material;
import Objects.Weapon;

public class Sword extends Weapon {
        public Sword(Material material) {
            super(material,
                    Material.getName()+" Sword" ,
                    Material.getValueMultiplier()*1.5,
                    3,
                    "Strength",
                    5*Material.getDamageMultiplier());
        }
}
