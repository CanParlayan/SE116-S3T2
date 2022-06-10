package Items;


import Objects.CharacterAttack;
import Objects.Material;
import Objects.Weapon;
import Objects.Character;

public class Sword extends Weapon {
        public Sword(Material material) {
            super(material,
                    Material.getName()+" Sword" ,
                    Material.getValueMultiplier()*1.5,
                    5,
                    "Strength",
                    (int) (0.5*Material.getDamageMultiplier()));
        }
    public long disengage(Character character){
        return Math.round(character.getStrength()/2);
    }
}
