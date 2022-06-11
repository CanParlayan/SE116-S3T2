package Items;

import Objects.CharacterAttack;
import Objects.Enemy;
import Objects.Material;
import Objects.Weapon;
import Objects.Character;

public class Shield extends Weapon {
        public Shield(Material material) {
            super(material,
                    Material.getName()+" Shield" ,
                    Material.getValueMultiplier()*3,
                    4,
                    "Vitality",
                    5*Material.getDamageMultiplier());
        }
    public static long stun(Character character, Enemy enemy){
        return Math.round(character.CalculateDamage()/enemy.getVitality());
    }
}
