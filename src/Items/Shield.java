package Items;

import Objects.CharacterAttack;
import Objects.Material;
import Objects.Weapon;

public class Shield extends Weapon implements CharacterAttack {
        public Shield(Material material) {
            super(material,
                    Material.getName()+"Shield" ,
                    Material.getValueMultiplier()*3,
                    11,
                    "Vitality",
                    5*Material.getDamageMultiplier());
        }
    public void stun(){

    }

    public void specialAction() {
        stun();
    }
}
