package Items;

import Objects.Armor;
import Objects.Material;


public class Chainmail extends Armor {
    public Chainmail(Material material) {
        super(material,
                Material.getName() + " Chainmail Armor",
                Material.getValueMultiplier() * 4.5,
                30.0,
                5);
    }
}

