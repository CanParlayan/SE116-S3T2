package Objects;

import Materials.*;
import Items.*;
import Items.Wand;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Initiliaze {

    private static final String
            askUser = "> What do you want to do?",
            welcomeUser = """
 
                    Welcome to the game.
                    You can type quit to exit the game.""";
    private static final String[]
            openInventory = {"inventory"}, attack = {"attack"}, stop = {"quit"}, displayStats = {"display"},
            examine = {"examine"};
    Material crystal = new Crystal();
    Material steel = new Steel();
    Material mithril = new Mithril();

    Player player = new Player();
    public int highScore = 0;
    boolean running = true;
    Scanner scan = new Scanner(System.in);
    Random rand = new Random();
    Level level1 = new Level();


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
    Character tank = new Character("Tank", level1);
    Character healer = new Character("Healer", level1);
    Character fighter = new Character("Fighter", level1);
    Character enemy = new Character("Enemy",level1);

    public static HashMap<String, Weapon> allWeapons = new HashMap<>();
    public static HashMap<String, Armor> allArmors = new HashMap<>();
    public static HashMap<String, Item> allItems = new HashMap<>();

    private void initializeItems() {
        allWeapons.put("mithrilwand", mithrilWand);
        allWeapons.put("longsword", crystalWand);
        allWeapons.put("steelwand", steelWand);
        allWeapons.put("steelSword", steelSword);
        allWeapons.put("mithrilsword", mithrilSword);
        allWeapons.put("crystalsword", crystalSword);
        allWeapons.put("steelshield", steelShield);
        allWeapons.put("mithrilshield", mithrilShield);
        allWeapons.put("crystalshield", crystalShield);
        allArmors.put("crystalpadded", crystalPadded);
        allArmors.put("steelpadded", steelPadded);
        allArmors.put("mithrilpadded", mithrilPadded);
        allArmors.put("crystalchainmail", crystalChainmail);
        allArmors.put("steelchainmail", steelChainmail);
        allArmors.put("mithrilchainmail", mithrilChainmail);
        allArmors.put("crystalfullplate", crystalFullPlate);
        allArmors.put("steelfullplate", steelFullPlate);
        allArmors.put("mithrilfullplate", mithrilFullPlate);
        allItems.putAll(allWeapons);
        allItems.putAll(allArmors);
    }

    private void gameStart() {
        player.getName();
        tank.setHeldWeapon(mithrilShield);
        tank.setHeldArmor(mithrilPadded);
        healer.setHeldWeapon(mithrilWand);
        healer.setHeldArmor(mithrilPadded);
        fighter.setHeldWeapon(mithrilSword);
        fighter.setHeldArmor(mithrilPadded);
        level1.AddToLevelDrops(mithrilPadded);
        level1.AddToLevelDrops(mithrilSword);


    }

    private void Input(String input) {

        input = input.replaceAll("\\s+", " ");


        input = input.trim();


        String[] words = input.split(" ");


        if (words.length == 0) {
            return;
        }
        if (inputEquals(words, new String[]{"tank"},new String[]{"throw"})) {
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
            }
            Item item = allItems.get(itemName);
            if (item != null)
                tank.Throw(item);
        } else if (inputEquals(words, new String[]{"healer"},new String[]{"throw"})) {
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
            }
            Item item = allItems.get(itemName);
            if (item != null)
                healer.Throw(item);
        } else if (inputEquals(words, new String[]{"fighter"},new String[]{"throw"})){
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
            }
            Item item = allItems.get(itemName);
            if (item != null)
                fighter.Throw(item);
        }
        if (inputEquals(words, new String[]{"tank"},new String[]{"pick"})){
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
            }
            Item item = allItems.get(itemName);
            if (item != null)
                tank.Pick(item);
            level1.RemoveFromLevelDrops(item);
        } else if (inputEquals(words, new String[]{"fighter"},new String[]{"pick"})) {
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
            }
            Item item = allItems.get(itemName);
            if (item != null)
                fighter.Pick(item);
            level1.RemoveFromLevelDrops(item);
        } else if (inputEquals(words, new String[]{"healer"},new String[]{"pick"})) {
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
            }
            Item item = allItems.get(itemName);
            if (item != null)
                healer.Pick(item);
            level1.RemoveFromLevelDrops(item);
        }
        if (inputEquals(words, new String[]{"tank"},new String[]{"wear"})) {
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
            }
            Armor item = allArmors.get(itemName);
            if (item != null)
                tank.Wear(item);
        } else if (inputEquals(words, new String[]{"fighter"},new String[]{"wear"})) {
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
            }
            Armor item = allArmors.get(itemName);
            if (item != null)
                fighter.Wear(item);
        } else if (inputEquals(words, new String[]{"healer"},new String[]{"wear"})) {
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
            }
            Armor item = allArmors.get(itemName);
            if (item != null)
                healer.Wear(item);
        }
        if (inputEquals(words, new String[]{"tank"},new String[]{"wield"})) {
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
            }Weapon item = allWeapons.get(itemName);
            if (item != null)
            tank.Wield(item);
        }
        else if (inputEquals(words, new String[]{"healer"},new String[]{"wield"})) {
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
            }Weapon item = allWeapons.get(itemName);
            if (item != null)
                healer.Wield(item);
        }
        else if (inputEquals(words, new String[]{"fighter"},new String[]{"wield"})) {
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
            }Weapon item = allWeapons.get(itemName);
            if (item != null)
                fighter.Wield(item);
        }
        if (inputEquals(words, openInventory)) {
            System.out.println("Tank's Inventory:");
            for(Item item : tank.getInventory())
                System.out.println(item.getItemName());
            System.out.println("Healer's Inventory:");
            for(Item item : healer.getInventory())
                System.out.println(item.getItemName());
            System.out.println("Fighter's Inventory:");
            for(Item item : fighter.getInventory())
                System.out.println(item.getItemName());
            return;
        }
        if (inputEquals(words, new String[]{"fighter"},displayStats)) {
            fighter.DisplayStats();
        }
        else if (inputEquals(words, new String[]{"healer"},displayStats)) {
            healer.DisplayStats();
        }
        else if (inputEquals(words, new String[]{"tank"},displayStats)) {
            tank.DisplayStats();
        }
        if (inputEquals(words, new String[]{"look"})) {
            System.out.println("Items on the ground:");
            for (Item item : level1.getLevelDrops())
                if (item == null) {
                    System.out.println("No item on the ground");
                } else {
                    System.out.println(item.getItemName());
                }
        }
        if(inputEquals(words,examine)){
            String itemName = words[1];
            for (int i = 2; i < words.length; i++) {
                itemName += " " + words[i];
            }Item item = allItems.get(itemName);
            if (item != null)
                healer.Examine(item);
        }
        if (inputEquals(words, new String[]{"tank"},new String[]{"stun"})) {
            String enemyName = words[2];
            for (int i = 3; i < words.length; i++) {
                enemyName += " " + words[i];
            }
            if (tank.getHeldWeapon() instanceof Shield) {
            }
        }

       /*if (inputEquals(words, attack)) {
            if (currentRoom.getEnemy() != null) {
                System.out.println("You engaged in a fight with a " + currentRoom.getEnemy().getEnemyName());
                System.out.println("You have " + hero.getHp() + " hp.");
                System.out.println("Your weapon deals " + hero.getPlayerDamage() + " damage");
                System.out.println(currentRoom.getEnemy().getEnemyName() + " can deal you " +
                        currentRoom.getEnemy().getEnemyDamage() + " damage");
                while (hero.getHp() > 0 && currentRoom.getEnemy().getEnemyHealth() > 0) {
                    System.out.println(currentRoom.getEnemy().getEnemyName() + " has " +
                            currentRoom.getEnemy().getEnemyHealth() + " hp.");
                    System.out.println(askUser);
                    System.out.println("Type 1 for attacking the enemy");
                    System.out.println("Type 2 for drinking a health potion");
                    int fightInput = scan.nextInt();
                    switch (fightInput) {
                        case 1:
                            currentRoom.getEnemy().takeDamage(hero.getPlayerDamage());
                            hero.takeDamage(currentRoom.getEnemy().getEnemyDamage() - hero.getCurrentlyWornArmor().getGiveArmor());
                            System.out.println("Your armor protected you from " +
                                    hero.getCurrentlyWornArmor().getGiveArmor());
                            System.out.println("You have taken " + (currentRoom.getEnemy().getEnemyDamage() -
                                    hero.getCurrentlyWornArmor().getGiveArmor()) + " damage");
                            System.out.println("You gave enemy " + hero.getPlayerDamage() + " damage");
                            System.out.println("You have " + hero.getHp() + " hp.");
                            break;
                        case 2:
                            drinkPot();
                            break;
                        default:
                            System.out.println("Enter a valid command");
                    }
                }
                if (hero.getHp() <= 0) {

                    System.out.println("You died " + hero.getName());
                    System.out.println("Number of enemies you killed : " + totalDeadEnemyCount);
                    System.out.println("Number of people you saved : " + savedTownsPeopleCount);
                    highScore = this.getValues();
                    saveScore();
                    System.exit(1);

                } else if (currentRoom.getEnemy().getEnemyHealth() <= 0) {
                    System.out.println("You killed the " + currentRoom.getEnemy().getEnemyName());
                    currentRoom.addItem(currentRoom.getEnemy().getDropsItem());
                    System.out.println("Enemy dropped " + currentRoom.getEnemy().getDropsItem().getItemName());

                    System.out.println("You saved a human from the hands of the " +
                            currentRoom.getEnemy().getEnemyName());
                    currentRoom.setEnemy(null);
                    levelHeroKillCount++;
                    totalDeadEnemyCount++;
                    savedTownsPeopleCount++;
                    if (rand.nextInt(100) < townsPeopleHealChance) {
                        System.out.println("The human you save gave you a food");
                        hero.setHp(hero.getHp() + townsPeopleHealAmount);
                        System.out.println("Food healed you for " + townsPeopleHealAmount + "hp.");

                    } else {
                        System.out.println("The person you saved thanked you.");
                    }
                }
                scan.nextLine();
            } else if (currentRoom.getEnemy() == null){
                System.out.println("There is nothing to fight in this room");
            }
        }/*
       /*if (inputEquals(words, openInventory)) {
            System.out.println("Inventory:");
            if (tank.inventory.isEmpty()) {
                System.out.println(" - There is nothing in here.");
            } else {
                hero.inventory.printItems(" - %s\n");
            }
            return;
        }*/
        if (inputEquals(words, stop)) {
            System.out.println("You decided to run away and didn't accomplished what you have came for");
            System.out.println("You left all your items while escaping");
            System.out.println("You will never be known as an hero. Those who know you will call you a coward");
            System.exit(1);
            return;
        }

    }
    private static boolean inputEquals (String[]words, String[]...userInput){
        if (words.length < userInput.length) {
            return false;
        }
        for (int i = 0; i < userInput.length; i++) {
            String word = words[i];
            String[] possibleWords = userInput[i];
            if (!equalsAny(word, possibleWords)) {
                return false;
            }
        }return true;
    }
    private static boolean equalsAny (String word, String[]possibleWords){
        for (String possibility : possibleWords) {
            if (word.equalsIgnoreCase(possibility)) {
                return true;
            }
        }return false;
    }
    public void run() {

        //reader.close(); do not forgot
        initializeItems();
        gameStart();
        System.out.println(welcomeUser);
        while (running) {
            System.out.println(askUser);
            String start = scan.nextLine();
            Input(start);


        }

    }
    public void saveScore() {
        FileWriter writeFile = null;
        BufferedWriter writer = null;
        try {
            writeFile = new FileWriter("highscore.txt");
            writer = new BufferedWriter(writeFile);
            writer.write(highScore);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}