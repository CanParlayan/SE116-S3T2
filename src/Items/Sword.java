package Items;


import Objects.CharacterAttack;
import Objects.Material;
import Objects.Weapon;

public class Sword extends Weapon implements CharacterAttack {
        public Sword(Material material) {
            super(material,
                    Material.getName()+"Sword" ,
                    Material.getValueMultiplier()*7,
                    5,
                    "Strength",
                    9*Material.getDamageMultiplier());
        }
    public void keepFree(){

    }

    public void specialAction() {
        keepFree();
    }
}
