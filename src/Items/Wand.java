package Items;

import Objects.Character;
import Objects.Material;
import Objects.Weapon;



public class Wand extends Weapon {
    public Wand(Material material) {
        super(material,
                Material.getName()+" Wand",
                Material.getValueMultiplier()*5,
                3,
                "Intelligence",
                4*Material.getDamageMultiplier());
    }
    public double heal(Character character){
        return Math.round(character.getIntelligence()/1.5);
    }
}