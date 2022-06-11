package Items;

import Objects.Armor;
import Objects.Material;


public class Chainmail extends Armor {
    public Chainmail(Material material,String itemName) {
        super(material,
                itemName,
                Material.getValueMultiplier() * 4.5,
                3,
                5);
    }
}

