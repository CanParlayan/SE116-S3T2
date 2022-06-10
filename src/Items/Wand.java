package Items;
import Objects.*;
import Materials.Mithril;
import Objects.Character;
import Objects.CharacterAttack;
import Objects.Material;
import Objects.Weapon;



public class Wand extends Weapon {
    public Wand(Material material) {
        super(material,
                Material.getName()+" Wand",
                Material.getValueMultiplier()*5,
                3,
                "Intelligence",
                5*Material.getDamageMultiplier());
    }
    public double heal(Character character){
        return Math.round(character.getIntelligence()/2);
    }
}