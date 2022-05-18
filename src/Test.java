import Objects.*;
import Materials.*;
import Items.*;

public class Test {
    public static void main(String[] args) {
        Material crystal = new Crystal();
        Material steel = new Steel();
        Material mithril = new Mithril();
        PlayableHero fighter = new PlayableHero(4,9,6,"Fighter");
        PlayableHero healer = new PlayableHero(8,3,2,"Healer");
        PlayableHero tank = new PlayableHero(1,6,10,"Tank");
        Weapon mithrilWand = new Wand(mithril);
        Weapon crystalWand = new Wand(crystal);
        Weapon steelSword = new Sword(steel);
        Weapon mithrilSword = new Sword(mithril);
        Weapon crystalSword = new Sword(crystal);
        Weapon steelShield = new Shield(steel);
        Weapon mithrilShield = new Shield(mithril);
        Weapon crystalShield = new Shield(crystal);
        Armor crystalPadded = new Padded(crystal);
        Armor steelPadded = new Padded(steel);
        Armor mithrilPadded = new Padded(mithril);
        Armor crystalChainmail = new Chainmail(crystal);
        Armor steelChainmail = new Chainmail(steel);
        Armor mithrilChainmail = new Chainmail(mithril);
        Armor crystalFullPlate = new FullPlate(crystal);
        Armor steelFullPlate = new FullPlate(steel);
        Armor mithrilFullPlate = new FullPlate(mithril);
        System.out.println(mithrilFullPlate.getArmorValue());
        System.out.println(crystalFullPlate.getItemName());
        System.out.println(steelChainmail.getValue());
        System.out.println(mithrilPadded.getWeight());
        System.out.println(crystalSword.getValue());
        System.out.println(mithrilWand.getDamage());
        System.out.println(steelShield.getItemName());
        System.out.println(crystalShield.getWeaponType());
        System.out.println(mithrilShield.getWeight());
    }
}