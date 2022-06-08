package Items;


import Objects.CharacterAttack;
import Objects.Material;
import Objects.Weapon;
import Objects.Character;

public class Sword extends Weapon {
        public Sword(Material material) {
            super(material,
                    Material.getName()+" Sword" ,
                    Material.getValueMultiplier()*7,
                    5,
                    "Strength",
                    9*Material.getDamageMultiplier());
        }
    public long disengage(Character character){
        return Math.round(character.getStrength()/2);
    }
}
