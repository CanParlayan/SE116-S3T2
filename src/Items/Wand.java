package Items;
import Objects.*;
import Materials.Mithril;
import Objects.CharacterAttack;
import Objects.Material;
import Objects.Weapon;



public class Wand extends Weapon implements CharacterAttack {
    public Wand(Material material) {
        super(material,
                Material.getName()+"Wand",
                Material.getValueMultiplier()*5,
                3,
                "Intelligence",
                5*Material.getDamageMultiplier());
    }
    public void heal(){

    }

    public void specialAction() {
        heal();
    }

}
