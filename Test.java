import Objects.*;
import Materials.*;
import Items.*;
import Objects.Character;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Test {

    private static final String
            askUser = "What do you want to do?",
            welcomeUser = """

                    Welcome to the game.
                    You can type quit to exit the game.""";
        Material crystal = new Crystal();
        Material steel = new Steel();
        Material mithril = new Mithril();

        Player player = new Player();
        public int highScore = 0;
        boolean running = true;
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        Weapon steelWand = new Wand(steel);
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
        Level level1 = new Level();
        public static HashMap<String, Weapon> allWeapons = new HashMap<>();
        public static HashMap<String, Armor> allArmors = new HashMap<>();
        public static HashMap<String, Item> allItems = new HashMap<>();

    private void initializeItems() {
        allWeapons.put("mithrilwand", mithrilWand);
        allWeapons.put("longsword", crystalWand);
        allWeapons.put("steelwand",steelWand);
        allWeapons.put("steelSword", steelSword);
        allWeapons.put("mithrilsword", mithrilSword);
        allWeapons.put("crystalsword",crystalSword);
        allWeapons.put("steelshield",steelShield);
        allWeapons.put("mithrilshield",mithrilShield);
        allWeapons.put("crystalshield",crystalShield);
        allArmors.put("crystalpadded",crystalPadded);
        allArmors.put("steelpadded",steelPadded);
        allArmors.put("mithrilpadded",mithrilPadded);
        allArmors.put("crystalchainmail",crystalChainmail);
        allArmors.put("steelchainmail",steelChainmail);

        allItems.putAll(allWeapons);
        allItems.putAll(allArmors);
    }

        private void gameStart() throws InvalidCharClassException {
            player.getName();
            System.out.println("Welcome to the game");
            Character fighter = new Character("fighter",level1);
            Character healer = new Character("healer",level1);
            Character tank = new Character("tank",level1);




        }
    }
}