package Objects;

import Items.*;
import Materials.Crystal;
import Materials.Mithril;
import Materials.Steel;

import java.io.*;
import java.util.*;

public class Initialize {


    private static final String
            askUser = "> What do you want to do?";
    private static final String[]
            openInventory = {"inventory"}, attack = {"attack"}, stop = {"quit"}, displayStats = {"stats"},
            examine = {"examine"}, specialAction = {"special"}, next = {"NEXT"};
    static Material crystal = new Crystal();
    static Material steel = new Steel();
    static Material mithril = new Mithril();

    Player player = new Player();
    private int score = 0;
    public int highScore = 0;
    String newHighScore;
    boolean running = true;
    Scanner scan = new Scanner(System.in);
    static Random rand = new Random(System.currentTimeMillis());
    static Level level1 = new Level();
    int turn = 3;
    int stunCooldown = 0;
    int invincibleCooldown = 0;


    static Weapon steelWand = new Wand(steel,"Steel Wand");
    static Weapon crystalWand = new Wand(crystal,"Crystal Wand");
    static Weapon steelSword = new Sword(steel,"Steel Sword");
    static Weapon mithrilWand = new Wand(mithril,"Mithril Wand");
    static Weapon mithrilShield = new Shield(mithril,"Mithril Shield");
    static Weapon mithrilSword = new Sword(mithril,"Mithril Sword");
    static Weapon crystalSword = new Sword(crystal,"Crystal Sword");
    static Weapon steelShield = new Shield(steel,"Steel Shield");
    static Weapon crystalShield = new Shield(crystal,"Crystal Shield");
    static Armor crystalPadded = new Padded(crystal,"Crystal Padded");
    static Armor steelPadded = new Padded(steel,"Steel Padded");
    static Armor mithrilPadded = new Padded(mithril,"Mithril Padded");
    static Armor crystalChainmail = new Chainmail(crystal,"Crystal Chainmail");
    static Armor steelChainmail = new Chainmail(steel,"Steel Chainmail");
    static Armor mithrilChainmail = new Chainmail(mithril,"Mithril Chainmail");
    static Armor crystalFullPlate = new FullPlate(crystal,"Crystal Full Plate");
    static Armor steelFullPlate = new FullPlate(steel,"Steel Full Plate");
    static Armor mithrilFullPlate = new FullPlate(mithril,"Mithril Full Plate");
    static Character tank = new Character("Tank", level1, steelShield, steelFullPlate);
    static Character healer = new Character("Healer", level1, steelWand, steelFullPlate);
    static Character fighter = new Character("Fighter", level1, steelSword, steelFullPlate);

    public static ArrayList<Character> characters = new ArrayList<>();

    static {
        characters.add(tank);
        characters.add(healer);
        characters.add(fighter);
    }

    public static HashMap<String, Weapon> allWeapons = new HashMap<>();
    public static HashMap<String, Armor> allArmors = new HashMap<>();
    public static HashMap<String, Item> allItems = new HashMap<>();
    public static HashMap<String, Weapon> allSwords = new HashMap<>();
    public static HashMap<String, Weapon> allShields = new HashMap<>();
    public static HashMap<String, Weapon> allWands = new HashMap<>();
    public static HashMap<String, Character> allEnemies = new HashMap<>();
    public int n = 1;

    public static Weapon EnemyRandomWeapon() { //probably very unbalanced gameplay wise because every material gets the same weight, but well, adds randomness into runs
        int swordDropChance = 40;
        int shieldDropChance = 30;
        int wandDropChance = 30;
        int randomNumber = rand.nextInt((swordDropChance + shieldDropChance + wandDropChance + 1));
        if (randomNumber < swordDropChance) {
            List<String> swordsAsList = new ArrayList<>(allSwords.keySet());//since the hashmaps are keyed in strings, I just throw all keys into an arraylist and randomize over it. Learnt this just now.
            return allSwords.get(swordsAsList.get(rand.nextInt(swordsAsList.size())));
        } else if (randomNumber < (swordDropChance + shieldDropChance)) {
            List<String> shieldsAsList = new ArrayList<>(allShields.keySet());
            return allShields.get(shieldsAsList.get(rand.nextInt(shieldsAsList.size())));
        } else {
            List<String> wandsAsList = new ArrayList<>(allWands.keySet());
            return allWands.get(wandsAsList.get(rand.nextInt(wandsAsList.size())));
        }
    }

    public static Armor EnemyRandomArmor() {
        Random random = new Random();
        List<String> armorsAsList = new ArrayList<>(allArmors.keySet());
        return allArmors.get(armorsAsList.get(rand.nextInt(armorsAsList.size())));
    }

    private void initializeObjects() {

        allWands.put("crystalwand", crystalWand);
        allWands.put("steelwand", steelWand);
        allSwords.put("steelsword", steelSword);
        allWands.put("mithrilwand", mithrilWand);
        allSwords.put("mithrilsword", mithrilSword);
        allShields.put("mithrilshield", mithrilShield);
        allSwords.put("crystalsword", crystalSword);
        allShields.put("steelshield", steelShield);
        allShields.put("crystalshield", crystalShield);
        allArmors.put("crystalpadded", crystalPadded);
        allArmors.put("steelpadded", steelPadded);
        allArmors.put("mithrilpadded", mithrilPadded);
        allArmors.put("crystalchainmail", crystalChainmail);
        allArmors.put("steelchainmail", steelChainmail);
        allArmors.put("mithrilchainmail", mithrilChainmail);
        allArmors.put("crystalfullplate", crystalFullPlate);
        allArmors.put("steelfullplate", steelFullPlate);
        allArmors.put("mithrilfullplate", mithrilFullPlate);
        allWeapons.putAll(allSwords);
        allWeapons.putAll(allShields);
        allWeapons.putAll(allWands);
        allItems.putAll(allWeapons);
        allItems.putAll(allArmors);
        enemySpawner(n, allEnemies);
    }

    private void gameStart() {
        player.getName();
        tank.setHeldWeapon(mithrilShield);
        tank.setHeldArmor(mithrilPadded);
        healer.setHeldWeapon(mithrilWand);
        healer.setHeldArmor(mithrilPadded);
        fighter.setHeldWeapon(mithrilSword);
        fighter.setHeldArmor(mithrilPadded);
        level1.AddToLevelDrops(steelFullPlate);
        level1.AddToLevelDrops(mithrilSword);
        level1.AddToLevelDrops(crystalSword);
        level1.AddToLevelDrops(steelSword);
    }

    public static void enemySpawner(int power, HashMap<String, Character> enemyMap) {
        for (int spctr = 0; spctr < Math.pow(2, power); spctr++) {
            String newEnemy = "enemy" + spctr;
            enemyMap.put(newEnemy, new Enemy(rand.nextInt(15)+1,rand.nextInt(15)+1,rand.nextInt(14)+1,rand.nextInt(40)+1,level1, EnemyRandomWeapon(), EnemyRandomArmor()));
        }
    }

    private void Input(String input) {

        input = input.replaceAll("\\s+", " ");


        input = input.trim();


        String[] words = input.split(" ");


        if (words.length == 0) {
            return;
        }
        if (inputEquals(words, new String[]{"tank"}, new String[]{"throw"})) {
            if(!tank.getIsDead()){
                StringBuilder itemName = new StringBuilder(words[2]);
                for (int i = 3; i < words.length; i++) {
                    itemName.append(" ").append(words[i]);
                }
                turn--;
                Item item = allItems.get(itemName.toString());
                if (item != null)
                    tank.Throw(item);
            }

            else{
                System.out.println(tank.getCharClass()+" is dead. This can not be done");
            }
        } else if (inputEquals(words, new String[]{"healer"}, new String[]{"throw"})) {
            if(!healer.getIsDead()){
                StringBuilder itemName = new StringBuilder(words[2]);
                for (int i = 3; i < words.length; i++) {
                    itemName.append(" ").append(words[i]);
                }
                turn--;
                Item item = allItems.get(itemName.toString());
                if (item != null)
                    healer.Throw(item);
            }
            else{
                System.out.println(healer.getCharClass()+" is dead. This can not be done");
            }
            turnCounter();
        }else if (inputEquals(words, new String[]{"fighter"}, new String[]{"throw"})) {
            if(!fighter.getIsDead()){
                StringBuilder itemName = new StringBuilder(words[2]);
                for (int i = 3; i < words.length; i++) {
                    itemName.append(" ").append(words[i]);
                }
                turn--;
                Item item = allItems.get(itemName.toString());
                if (item != null)
                    fighter.Throw(item);
            }
            else{
                System.out.println(fighter.getCharClass()+" is dead. This can not be done");
            }
            turnCounter();
        }if (inputEquals(words, new String[]{"tank"}, new String[]{"pick"})) {
            if(!tank.getIsDead()){
                StringBuilder itemName = new StringBuilder(words[2]);
                for (int i = 3; i < words.length; i++) {
                    itemName.append(" ").append(words[i]);
                }
                turn--;
                Item item = allItems.get(itemName.toString());
                if (item != null)
                    tank.Pick(item);
                level1.RemoveFromLevelDrops(item);
            }
            else{
                System.out.println(tank.getCharClass()+" is dead. This can not be done");
            }
            turnCounter();
        }
    else if (inputEquals(words, new String[]{"fighter"}, new String[]{"pick"})) {
        if(!fighter.getIsDead()){
            StringBuilder itemName = new StringBuilder(words[2]);
            for (int i = 3; i < words.length; i++) {
                itemName.append(" ").append(words[i]);
            }
            Item item = allItems.get(itemName.toString());
            if (item != null)
                fighter.Pick(item);
            level1.RemoveFromLevelDrops(item);
            turn--;
        }
        else{
            System.out.println(fighter.getCharClass()+" is dead. This can not be done");
        }
        turnCounter();
    } else if (inputEquals(words, new String[]{"healer"}, new String[]{"pick"})) {
        if(!healer.getIsDead()){
            StringBuilder itemName = new StringBuilder(words[2]);
            for (int i = 3; i < words.length; i++) {
                itemName.append(" ").append(words[i]);
            }
            turn--;
            Item item = allItems.get(itemName.toString());
            if (item != null)
                healer.Pick(item);
            level1.RemoveFromLevelDrops(item);
        }
        else{
            System.out.println(healer.getCharClass()+" is dead. This can not be done");
        }
        turnCounter();
    }
        if (inputEquals(words, new String[]{"tank"}, new String[]{"wear"})) {
        if(!tank.getIsDead()){
            StringBuilder itemName = new StringBuilder(words[2]);
            for (int i = 3; i < words.length; i++) {
                itemName.append(" ").append(words[i]);
            }
            turn--;
            Armor item = allArmors.get(itemName.toString());
            if (item != null)
                tank.Wear(item);
        }
        else {
            System.out.println(tank.getCharClass()+" is dead. This can not be done");
        }
        turnCounter();
    } else if (inputEquals(words, new String[]{"fighter"}, new String[]{"wear"})) {
        if(!fighter.getIsDead()){
            StringBuilder itemName = new StringBuilder(words[2]);
            for (int i = 3; i < words.length; i++) {
                itemName.append(" ").append(words[i]);
            }
            turn--;
            Armor item = allArmors.get(itemName.toString());
            if (item != null)
                fighter.Wear(item);
        }
        else {
            System.out.println(fighter.getCharClass()+" is dead. This can not be done");
        }
        turnCounter();
    } else if (inputEquals(words, new String[]{"healer"}, new String[]{"wear"})) {
        if(!healer.getIsDead()){
            StringBuilder itemName = new StringBuilder(words[2]);
            for (int i = 3; i < words.length; i++) {
                itemName.append(" ").append(words[i]);
            }
            turn--;
            Armor item = allArmors.get(itemName.toString());
            if (item != null)
                healer.Wear(item);
        }
        else {
            System.out.println(healer.getCharClass()+" is dead. This can not be done");
        }
        turnCounter();
    }
        if (inputEquals(words, new String[]{"tank"}, new String[]{"wield"})) {
        if(!tank.getIsDead()){
            StringBuilder itemName = new StringBuilder(words[2]);
            for (int i = 3; i < words.length; i++) {
                itemName.append(" ").append(words[i]);
            }
            turn--;
            Weapon item = allWeapons.get(itemName.toString());
            if (item != null)
                tank.Wield(item);
        }
        else {
            System.out.println(tank.getCharClass()+" is dead. This can not be done");
        }
        turnCounter();
    } else if (inputEquals(words, new String[]{"healer"}, new String[]{"wield"})) {
        if(!healer.getIsDead()){
            StringBuilder itemName = new StringBuilder(words[2]);
            for (int i = 3; i < words.length; i++) {
                itemName.append(" ").append(words[i]);
            }
            turn--;
            Weapon item = allWeapons.get(itemName.toString());
            if (item != null)
                healer.Wield(item);
        }
        else {
            System.out.println(healer.getCharClass()+" is dead. This can not be done");
        }
        turnCounter();
    } else if (inputEquals(words, new String[]{"fighter"}, new String[]{"wield"})) {
        if(!fighter.getIsDead()){
            StringBuilder itemName = new StringBuilder(words[2]);
            for (int i = 3; i < words.length; i++) {
                itemName.append(" ").append(words[i]);
            }
            turn--;
            Weapon item = allWeapons.get(itemName.toString());
            if (item != null)
                fighter.Wield(item);
        }
        else {
            System.out.println(fighter.getCharClass()+" is dead. This can not be done");
        }
        turnCounter();
    }
        if (inputEquals(words, openInventory)) {
            System.out.println("Tank's Inventory:");
            for (Item item : tank.getInventory())
                System.out.println(item.getItemName());
            System.out.println("Healer's Inventory:");
            for (Item item : healer.getInventory())
                System.out.println(item.getItemName());
            System.out.println("Fighter's Inventory:");
            for (Item item : fighter.getInventory())
                System.out.println(item.getItemName());
            return;
        }
        if (inputEquals(words, new String[]{"fighter"}, displayStats)) {
            if(!fighter.getIsDead()){
                fighter.DisplayStats();
            }
        } else if (inputEquals(words, new String[]{"healer"}, displayStats)) {
            if(!healer.getIsDead()){
                healer.DisplayStats();
            }
        } else if (inputEquals(words, new String[]{"tank"}, displayStats)) {
            if(!tank.getIsDead()){
                tank.DisplayStats();
            }
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
        if (inputEquals(words,new String[]{"gamemode1"})) {
            System.out.println("You have tried to sleep in nether");
            System.out.println("You have fallen from the void");
            System.out.println("You have vented electrical");
            System.out.println("You have tried to swim in lava");
            System.out.println("/noclip ");
            commitSuicide();
        }
        if (inputEquals(words, new String[]{"enemies"})){
            checkEnemies();
        }
        if (inputEquals(words, examine)) {
            StringBuilder itemName = new StringBuilder(words[1]);
            for (int i = 2; i < words.length; i++) {
                itemName.append(" ").append(words[i]);
            }
            Item item = allItems.get(itemName.toString());
            if (item != null) {
                healer.Examine(item);
            }
            else{
                System.out.println("There is no such item");
            }
        }
        if (inputEquals(words, next)) {
            if (allEnemies.size() < 1) {
                System.out.println("You are heading to the level " +n);
                n++;
                enemySpawner(n, allEnemies);
                fighter.setHealth(fighter.getMaxHP());
                turn = 3;
                tank.setHealth(tank.getMaxHP());
                healer.setHealth(healer.getMaxHP());
                level1.EmptyLevelDrops();
                checkEnemies();

            } else{
                System.out.println("Not all enemies are dead.");
            }
        }
        if (inputEquals(words, new String[]{"healer"}, specialAction)) {       //special action later to be changed
            Scanner scanner = new Scanner(System.in);
            if (healer.getHeldWeapon() instanceof Wand) {
                System.out.println("Which character you want to heal?");
                for (Character character : characters) {
                    System.out.println(character.getCharClass() + " has " + character.getHealth() + " HP");
                }
                String specialInput = scanner.nextLine();
                specialInput = specialInput.toLowerCase();
                int index = 0;
                for (Character character : characters) {
                    if (character.getCharClass().toLowerCase().equals(specialInput)) {
                        break;
                    }
                    index++;
                }
                int heal = (int) ((Wand) healer.getHeldWeapon()).heal(fighter);
                characters.get(index).updateHP(heal);
                characters.get(index).setHealth(characters.get(index).getHealth() + heal);
                System.out.println("Updated HP of " + characters.get(index).getCharClass() + " is " + characters.get(index).getHealth());
                turn--;
            } else if (healer.getHeldWeapon() instanceof Sword) {
                if (invincibleCooldown > 0) {
                    System.out.println("The ability is on cooldown");
                } else {
                    healer.setInvincible(true);
                    System.out.println(healer.getCharClass() + " is now invincible");
                    invincibleCooldown = 2;
                    turn--;
                }
            } else if (tank.getHeldWeapon() instanceof Shield) {
                if (stunCooldown > 0) {
                    System.out.println("The ability is on cooldown");
                } else {

                    System.out.println("Which enemy you want to stun?");
                    List<String> enemiesAsList = new ArrayList<>(allEnemies.keySet());
                    for (String enemy : enemiesAsList) {
                        System.out.println(" " + enemy);
                    }
                    String enemyName = scanner.nextLine();
                    Character enemy = allEnemies.get(enemyName.toString());
                    if (enemy != null) {
                        enemy.setStunned(true);
                        System.out.println(tank.getCharClass() + " has now stunned the " + enemy.getCharClass());
                        stunCooldown = 3;
                        turn--;
                    } else {
                        System.out.println("Not a valid enemy");
                    }
                }
            }
            turnCounter();
        }
        if (inputEquals(words, new String[]{"fighter"}, specialAction)) {       //special action later to be changed
            Scanner scanner = new Scanner(System.in);
            if (fighter.getHeldWeapon() instanceof Wand) {
                System.out.println("Which character you want to heal?");
                for (Character character : characters) {
                    System.out.println(character.getCharClass() + " has " + character.getHealth());
                }
                String specialInput = scanner.nextLine();
                specialInput = specialInput.toLowerCase();
                int index = 0;
                for (Character character : characters) {
                    if (character.getCharClass().toLowerCase().equals(specialInput)) {
                        break;
                    }
                    index++;
                }
                int heal = (int) ((Wand) fighter.getHeldWeapon()).heal(fighter);
                characters.get(index).updateHP(heal); //updateHp yazılacak
                characters.get(index).setHealth(characters.get(index).getHealth() + heal);
                System.out.println("Updated HP of " + characters.get(index).getCharClass() + " is " + characters.get(index).getHealth());
                turn--;
            } else if (fighter.getHeldWeapon() instanceof Sword) {
                if (invincibleCooldown > 0) {
                    System.out.println("The ability is on cooldown");
                } else {
                    fighter.setInvincible(true);
                    System.out.println(fighter.getCharClass() + " is now invincible");
                    invincibleCooldown = 2;
                    turn--;
                }
            } else if (tank.getHeldWeapon() instanceof Shield) {
                if (stunCooldown > 0) {
                    System.out.println("The ability is on cooldown");
                } else {

                    System.out.println("Which enemy you want to stun?");
                    List<String> enemiesAsList = new ArrayList<>(allEnemies.keySet());
                    for (String enemy : enemiesAsList) {
                        System.out.println(" " + enemy);
                    }
                    String enemyName = scanner.nextLine();
                    Character enemy = allEnemies.get(enemyName.toString());
                    if (enemy != null) {
                        enemy.setStunned(true);
                        System.out.println(tank.getCharClass() + " has now stunned the " + enemy.getCharClass());
                        stunCooldown = 3;
                        turn--;
                    } else {
                        System.out.println("Not a valid enemy");
                    }
                }
            }
            turnCounter();
        }
        if (inputEquals(words, new String[]{"tank"}, specialAction)) {       //special action later to be changed
            Scanner scanner = new Scanner(System.in);
            if (tank.getHeldWeapon() instanceof Wand) {
                System.out.println("Which character you want to heal?");
                for (Character character : characters) {
                    System.out.println(character.getCharClass() + " has " + character.getHealth());
                }
                String specialInput = scanner.nextLine();
                specialInput = specialInput.toLowerCase();
                int index = 0;
                for (Character character : characters) {
                    if (character.getCharClass().toLowerCase().equals(specialInput)) {
                        break;
                    }
                    index++;
                }
                int heal = (int) ((Wand) tank.getHeldWeapon()).heal(tank);
                characters.get(index).updateHP(heal); //updateHp yazılacak
                characters.get(index).setHealth(characters.get(index).getHealth() + heal);
                System.out.println("Updated HP of " + characters.get(index).getCharClass() + " is " + characters.get(index).getHealth());
                turn--;
            } else if (tank.getHeldWeapon() instanceof Sword) {
                if (invincibleCooldown > 0) {
                    System.out.println("The ability is on cooldown");
                } else {
                    tank.setInvincible(true);
                    System.out.println(tank.getCharClass() + " is now invicible.");
                    invincibleCooldown = 2;
                    turn--;
                }
            } else if (tank.getHeldWeapon() instanceof Shield) {
                if (stunCooldown > 0) {
                    System.out.println("The ability is on cooldown");
                } else {

                    System.out.println("Which enemy you want to stun?");
                    List<String> enemiesAsList = new ArrayList<>(allEnemies.keySet());
                    for (String enemy : enemiesAsList) {
                        System.out.println(" " + enemy);
                    }
                    String enemyName = scanner.nextLine();
                    Character enemy = allEnemies.get(enemyName.toString());
                    if (enemy != null) {
                        enemy.setStunned(true);
                        System.out.println(tank.getCharClass() + " has now stunned the " + enemy.getCharClass());
                        stunCooldown = 3;
                        turn--;
                    } else {
                        System.out.println("Not a valid enemy");
                    }
                }
            }
            turnCounter();
        }
        if (inputEquals(words, new String[]{"fighter"}, attack)) {
            StringBuilder enemyName = new StringBuilder(words[2]);
            for (int i = 3; i < words.length; i++) {
                enemyName.append(" ").append(words[i]);
            }
            Character enemy = allEnemies.get(enemyName.toString());
            if (enemy != null) {
                int fighterDamage = fighter.CalculateDamage();
                enemy.TakeDamage(fighterDamage);
                if (enemy.getIsDead()) {
                    level1.AddToLevelDrops(enemy.getHeldWeapon());
                    allEnemies.remove(enemyName.toString());
                }
            }
            else {
                System.out.println("No enemy around with the name " + enemyName);
            }
            turn--;
            turnCounter();
        }
        if (inputEquals(words, new String[]{"tank"}, attack)) {
            StringBuilder enemyName = new StringBuilder(words[2]);
            for (int i = 3; i < words.length; i++) {
                enemyName.append(" ").append(words[i]);
            }
            Character enemy = allEnemies.get(enemyName.toString());
            if (enemy != null) {
                int tankDamage = tank.CalculateDamage();
                enemy.TakeDamage(tankDamage);
                if (enemy.getHealth() > 0) {
                    System.out.println("Enemy's current HP: " + enemy.getHealth());
                } else {
                    System.out.print("");
                }
                if (enemy.getHeldArmor() == null) {
                    System.out.println("");
                } else {
                    System.out.println("Enemy's current Armor: " + enemy.getHeldArmor().getArmorValue());
                }
                if (enemy.getIsDead()) {
                    level1.AddToLevelDrops(enemy.getHeldWeapon());
                    allEnemies.remove(enemyName.toString());
                }
            }
            turn--;
            turnCounter();
        }
        if (inputEquals(words, new String[]{"healer"}, attack)) {
            StringBuilder enemyName = new StringBuilder(words[2]);
            for (int i = 3; i < words.length; i++) {
                enemyName.append(" ").append(words[i]);
            }
            Character enemy = allEnemies.get(enemyName.toString());
            if (enemy != null) {
                int healerDamage = healer.CalculateDamage();
                enemy.TakeDamage(healerDamage);
                if (enemy.getHealth() > 0) {
                    System.out.println("Enemy's current HP: " + enemy.getHealth());
                } else {
                    System.out.print("");
                }
                if (enemy.getHeldArmor() == null) {
                    System.out.println("");
                } else {
                    System.out.println("Enemy's current Armor: " + enemy.getHeldArmor().getArmorValue());
                }
                if (enemy.getIsDead()) {
                    level1.AddToLevelDrops(enemy.getHeldWeapon());
                    allEnemies.remove(enemyName.toString());
                }
            }
            turn--;
            turnCounter();
        }
        if (inputEquals(words, stop)) {
            System.out.println("You decided to run away and didn't accomplished what you have came for");
            System.out.println("You left all your items while escaping");
            System.out.println("You will never be known as an hero. Those who know you will call you a coward");
            System.exit(1);
        }

    }

    private static boolean inputEquals(String[] words, String[]... userInput) {
        if (words.length < userInput.length) {
            return false;
        }
        for (int i = 0; i < userInput.length; i++) {
            String word = words[i];
            String[] possibleWords = userInput[i];
            if (!equalsAny(word, possibleWords)) {
                return false;
            }
        }
        return true;

    }

    private static boolean equalsAny(String word, String[] possibleWords) {
        for (String possibility : possibleWords) {
            if (word.equalsIgnoreCase(possibility)) {
                return true;
            }
        }
        return false;
    }

    public void checkEnemies() {
        if (allEnemies.size() < 1) //delete later
            System.out.println("No enemy around");
        else {
            for (String i : allEnemies.keySet()) {         // Invalid char class
                System.out.println("There is " + i);
            }
        }
    }
    void attackPlayer(Character enemy) {
        if (!tank.getIsDead() && !tank.isInvincible() && !enemy.isStunned()) {
            if(tank.getHeldArmor() != null){
                if (tank.getHeldArmor().getArmorValue() >= 0) {
                    if ((tank.getHeldArmor().getArmorValue() - enemy.getHeldWeapon().getDamage()) < 0) {
                        int temp = Math.abs(tank.getHeldArmor().getArmorValue() - enemy.getHeldWeapon().getDamage());
                        tank.getHeldArmor().setArmorValue(0);
                        tank.setHeldArmor(null);
                        System.out.println(tank.getCharClass() + "'s armor is broken.");
                        tank.setHealth(tank.getHealth() - temp);
                        System.out.println(tank.getCharClass() + " also took " + temp + " damage.");
                    } else {
                        tank.getHeldArmor().setArmorValue((int) (tank.getHeldArmor().getValue() - enemy.getHeldWeapon().getDamage()));
                        System.out.println(tank.getCharClass() + " took " + enemy.getHeldWeapon().getDamage() + " damage.");
                    }
                }
            } else {
                tank.setHealth(tank.getHealth() - enemy.getHeldWeapon().getDamage());
                System.out.println(tank.getCharClass() + " took " + enemy.getHeldWeapon().getDamage() + " damage.");
            }
            tank.CheckDead();
            turn = 3;
        } else if (tank.getIsDead() && !fighter.getIsDead() && !fighter.isInvincible() && !enemy.isStunned()) {
            if(fighter.getHeldArmor() != null){
                if (fighter.getHeldArmor().getArmorValue() >= 0) {
                    if ((fighter.getHeldArmor().getArmorValue()) - enemy.getHeldWeapon().getDamage() < 0) {
                        int temp = Math.abs(fighter.getHeldArmor().getArmorValue() - enemy.getHeldWeapon().getDamage());
                        fighter.getHeldArmor().setArmorValue(0);
                        fighter.setHeldArmor(null);
                        System.out.println(fighter.getCharClass() + "'s armor is broken.");
                        fighter.setHealth(fighter.getHealth() - temp);
                        System.out.println(fighter.getCharClass() + " took " + temp + " damage.");
                    } else {
                        fighter.getHeldArmor().setArmorValue((int) (fighter.getHeldArmor().getValue() - enemy.getHeldWeapon().getDamage()));
                        System.out.println(fighter.getCharClass() + " took " + enemy.getHeldWeapon().getDamage() + " damage.");
                    }
                } }else {
                fighter.setHealth(fighter.getHealth() - enemy.getHeldWeapon().getDamage());
                System.out.println(fighter.getCharClass() + " took " + enemy.getHeldWeapon().getDamage() + " damage.");
            }
            fighter.CheckDead();
            turn = 3;
        } else if (tank.getIsDead() && fighter.getIsDead() && !healer.getIsDead() && !healer.isInvincible() && !enemy.isStunned()) {
            if(healer.getHeldArmor() != null){
                if ((healer.getHeldArmor().getArmorValue()) >= 0) {
                    if (healer.getHeldArmor().getArmorValue() - enemy.getHeldWeapon().getDamage() < 0) {
                        int temp = Math.abs((healer.getHeldArmor().getArmorValue() - enemy.getHeldWeapon().getDamage()));
                        healer.getHeldArmor().setArmorValue(0);
                        healer.setHeldArmor(null);
                        System.out.println(healer.getCharClass() + "'s armor is broken.");
                        healer.setHealth(healer.getHealth() - temp);
                        System.out.println(healer.getCharClass() + " took " + temp + " damage.");
                    } else {
                        healer.getHeldArmor().setArmorValue((int) (healer.getHeldArmor().getValue() - enemy.getHeldWeapon().getDamage()));
                        System.out.println(healer.getCharClass() + " took " + enemy.getHeldWeapon().getDamage() + " damage.");
                    }
                } }else {
                healer.setHealth(healer.getHealth() - enemy.getHeldWeapon().getDamage());
                System.out.println(healer.getCharClass() + " took " + enemy.getHeldWeapon().getDamage() + " damage.");
            }
            healer.CheckDead();
            turn = 3;
        }
        if (tank.getHealth() <= 0) {
            tank.setIsDead(true);
        }
        if (healer.getHealth() <= 0){
            healer.setIsDead(true);
        }
        if (fighter.getHealth() <= 0){
            fighter.setIsDead(true);
        }
    }

    public void turnCounter() {
        System.out.println("Turns left :" + turn);
    }
    public void commitSuicide(){
        tank.setIsDead(true);
        fighter.setIsDead(true);
        healer.setIsDead(true);
    }

    public void run() throws InterruptedException, IOException {

        //reader.close(); do not forgot
        initializeObjects();
        gameStart();
        System.out.println("Welcome " + player.getName());
        Intro.OpeningText();
        loadHighscore();
        while (running) {
            if (tank.getIsDead() && healer.getIsDead() && fighter.getIsDead()) {
                running = false;
                getValues();
                saveScore();
                Intro.Credits();
            } else {
                if (allEnemies.size() == 0) {
                    turn = 500;
                    System.out.println("All enemies are dead you can skip to the next level");
                    level1.AddToLevelDrops(EnemyRandomArmor());
                    System.out.println("You've stumbled upon an item or was it there all the time?");
                    System.out.println(askUser);
                    String start = scan.nextLine();
                    Input(start);
                } else {
                    if (turn > 0) {
                        System.out.println("It's your turn " + player.getName() + ".");
                        System.out.println(askUser);
                        String start = scan.nextLine();
                        Input(start);
                    } else {
                        System.out.println("Enemy's turn");
                        Thread.sleep(1000);
                        List<String> enemiesAsList = new ArrayList<>(allEnemies.keySet());
                        for (String enemy : enemiesAsList) {
                            attackPlayer(allEnemies.get(enemy));
                        }

                        fighter.setInvincible(false);
                        tank.setInvincible(false);
                        healer.setInvincible(false);
                        stunCooldown--;
                        invincibleCooldown--;
                        if (invincibleCooldown < 0) {
                            invincibleCooldown = 0;
                        }
                        if (stunCooldown < 0) {
                            stunCooldown = 0;
                        }
                        for (String enemy : enemiesAsList) {
                            allEnemies.get(enemy).setStunned(false);
                        }
                    }
                }
            }
        }
    }
    public void getValues(){
        for (Item item : fighter.getInventory()){
            score += item.getValue();
            score += fighter.getHeldWeapon().getValue();
            score += fighter.getHeldArmor().getValue();
        }
        for (Item item : tank.getInventory()){
            score += item.getValue();
            score += tank.getHeldWeapon().getValue();
            score += tank.getHeldArmor().getValue();
        }
        for (Item item : healer.getInventory()){
            score += item.getValue();
            score += healer.getHeldWeapon().getValue();
            score += healer.getHeldArmor().getValue();
        }
    }
    private void saveScore(){
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("highscore.txt", false));
            writer.write("" + player.getName() +":  " + score);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving highscore");
        }
    }
    private void loadHighscore(){
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader("highscore.txt"));
            line = br.readLine();
            br.close();
        } catch (IOException e) {
            line = "";
        }

        if(line != ""){
            newHighScore = ("Highscore: " + highScore);
        }
    }
}