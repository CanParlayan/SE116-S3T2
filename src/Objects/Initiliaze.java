package Objects;

import Items.*;
import Materials.Crystal;
import Materials.Mithril;
import Materials.Steel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.*;

public class Initiliaze {

    private static final String
            askUser = "> What do you want to do?",
            welcomeUser = """
                                       
                    Welcome to the game.
                    You can type quit to exit the game.""";
    private static final String[]
            openInventory = {"inventory"}, attack = {"attack"}, stop = {"quit"}, displayStats = {"display"},
            examine = {"examine"}, specialAction = {"special"}, next = {"NEXT"};
    static Material crystal = new Crystal();
    static Material steel = new Steel();
    static Material mithril = new Mithril();

    Player player = new Player();
    public int highScore = 0;
    boolean running = true;
    Scanner scan = new Scanner(System.in);
    static Random rand = new Random();
    static Level level1 = new Level();
    int turn = 3;
    int stunCooldown = 0;
    int invincibleCooldown = 0;


    static Weapon steelWand = new Wand(steel);
    static Weapon mithrilWand = new Wand(mithril);
    static Weapon crystalWand = new Wand(crystal);
    static Weapon steelSword = new Sword(steel);
    static Weapon mithrilSword = new Sword(mithril);
    static Weapon crystalSword = new Sword(crystal);
    static Weapon steelShield = new Shield(steel);
    static Weapon mithrilShield = new Shield(mithril);
    static Weapon crystalShield = new Shield(crystal);
    static Armor crystalPadded = new Padded(crystal);
    static Armor steelPadded = new Padded(steel);
    static Armor mithrilPadded = new Padded(mithril);
    static Armor crystalChainmail = new Chainmail(crystal);
    static Armor steelChainmail = new Chainmail(steel);
    static Armor mithrilChainmail = new Chainmail(mithril);
    static Armor crystalFullPlate = new FullPlate(crystal);
    static Armor steelFullPlate = new FullPlate(steel);
    static Armor mithrilFullPlate = new FullPlate(mithril);
    static Character tank = new Character("Tank", level1, mithrilShield, steelFullPlate);
    static Character healer = new Character("Healer", level1, mithrilWand, steelFullPlate);
    static Character fighter = new Character("Fighter", level1, mithrilSword, steelFullPlate);

    public static ArrayList<Character> characters = new ArrayList<>();

    static {
        characters.add(tank);
        characters.add(healer);
        characters.add(fighter);
    }

    public static HashMap<String, Weapon> allWeapons = new HashMap<>();
    public static HashMap<String, Armor> allArmors = new HashMap<>();
    public static HashMap<String, Item> allItems = new HashMap<>();
    public static HashMap<String, Weapon> allSwords = new HashMap<>(); //CanParlayan: make sure to initialize
    public static HashMap<String, Weapon> allShields = new HashMap<>();
    public static HashMap<String, Weapon> allWands = new HashMap<>();
    public static HashMap<String, Character> allEnemies = new HashMap<>();
    public int n = 1;

    public static Weapon EnemyRandomWeapon(){ //probably very unbalanced gameplay wise because every material gets the same weight, but well, adds randomness into runs
        Random random = new Random();
        int swordDropChance = 80;
        int shieldDropChance = 10;
        int wandDropChance = 10;
        int randomNumber = random.nextInt((swordDropChance + shieldDropChance + wandDropChance + 1));
        if (randomNumber < swordDropChance) {
            List<String> swordsAsList = new ArrayList<>(allSwords.keySet()); //since the hashmaps are keyed in strings, I just throw all keys into an arraylist and randomize over it. Learnt this just now.
            return allSwords.get(swordsAsList.get(random.nextInt(swordsAsList.size())));
        } else if (randomNumber < (swordDropChance + shieldDropChance)) {
            List<String> shieldsAsList = new ArrayList<>(allShields.keySet());
            return allShields.get(shieldsAsList.get(random.nextInt(shieldsAsList.size())));
        } else if (randomNumber <= (swordDropChance + shieldDropChance + wandDropChance)) {
            List<String> wandsAsList = new ArrayList<>(allWands.keySet());
            return allWands.get(wandsAsList.get(random.nextInt(wandsAsList.size())));
        }
        return null; //it drops nothing in the event above code fails to work
    }
    public static Armor EnemyRandomArmor(){
        Random random = new Random();
        List<String> armorsAsList = new ArrayList<>(allArmors.keySet());
        return allArmors.get(armorsAsList.get(random.nextInt(armorsAsList.size())));
    }

    private void initializeObjects() {
        allWands.put("mithrilwand", mithrilWand);
        allWands.put("longsword", crystalWand);
        allWands.put("steelwand", steelWand);
        allSwords.put("steelSword", steelSword);
        allSwords.put("mithrilsword", mithrilSword);
        allSwords.put("crystalsword", crystalSword);
        allShields.put("steelshield", steelShield);
        allShields.put("mithrilshield", mithrilShield);
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
        level1.AddToLevelDrops(mithrilPadded);
        level1.AddToLevelDrops(mithrilSword);
        level1.AddToLevelDrops(mithrilShield);
        level1.AddToLevelDrops(mithrilWand);

    }

    public static void enemySpawner(int power, HashMap<String, Character> enemyMap) {
        for (int spctr = 0; spctr < Math.pow(2, power); spctr++) {
            String newEnemy = "enemy" + spctr;
            enemyMap.put(newEnemy, new Enemy("Enemy", EnemyRandomWeapon(), EnemyRandomArmor()));
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
            StringBuilder itemName = new StringBuilder(words[2]);
            for (int i = 3; i < words.length; i++) {
                itemName.append(" ").append(words[i]);
                turn--;
            }
            Item item = allItems.get(itemName.toString());
            if (item != null)
                tank.Throw(item);
        } else if (inputEquals(words, new String[]{"healer"}, new String[]{"throw"})) {
            StringBuilder itemName = new StringBuilder(words[2]);
            for (int i = 3; i < words.length; i++) {
                itemName.append(" ").append(words[i]);
            }
            turn--;
            Item item = allItems.get(itemName.toString());
            if (item != null)
                healer.Throw(item);
            turnCounter();
        } else if (inputEquals(words, new String[]{"fighter"}, new String[]{"throw"})) {
            StringBuilder itemName = new StringBuilder(words[2]);
            for (int i = 3; i < words.length; i++) {
                itemName.append(" ").append(words[i]);
            }
            turn--;
            Item item = allItems.get(itemName.toString());
            if (item != null)
                fighter.Throw(item);
            turnCounter();
        }
        if (inputEquals(words, new String[]{"tank"}, new String[]{"pick"})) {
            StringBuilder itemName = new StringBuilder(words[2]);
            for (int i = 3; i < words.length; i++) {
                itemName.append(" ").append(words[i]);
            }
            turn--;
            Item item = allItems.get(itemName.toString());
            if (item != null)
                tank.Pick(item);
            level1.RemoveFromLevelDrops(item);
            turnCounter();
        } else if (inputEquals(words, new String[]{"fighter"}, new String[]{"pick"})) {
            StringBuilder itemName = new StringBuilder(words[2]);
            for (int i = 3; i < words.length; i++) {
                itemName.append(" ").append(words[i]);
            }
            turn--;
            Item item = allItems.get(itemName.toString());
            if (item != null)
                fighter.Pick(item);
            level1.RemoveFromLevelDrops(item);
            turnCounter();
        } else if (inputEquals(words, new String[]{"healer"}, new String[]{"pick"})) {
            StringBuilder itemName = new StringBuilder(words[2]);
            for (int i = 3; i < words.length; i++) {
                itemName.append(" ").append(words[i]);
            }
            turn--;
            Item item = allItems.get(itemName.toString());
            if (item != null)
                healer.Pick(item);
            level1.RemoveFromLevelDrops(item);
            turnCounter();
        }
        if (inputEquals(words, new String[]{"tank"}, new String[]{"wear"})) {
            StringBuilder itemName = new StringBuilder(words[2]);
            for (int i = 3; i < words.length; i++) {
                itemName.append(" ").append(words[i]);
            }
            turn--;
            Armor item = allArmors.get(itemName.toString());
            if (item != null)
                tank.Wear(item);
            turnCounter();
        } else if (inputEquals(words, new String[]{"fighter"}, new String[]{"wear"})) {
            StringBuilder itemName = new StringBuilder(words[2]);
            for (int i = 3; i < words.length; i++) {
                itemName.append(" ").append(words[i]);
            }
            turn--;
            Armor item = allArmors.get(itemName.toString());
            if (item != null)
                fighter.Wear(item);
            turnCounter();
        } else if (inputEquals(words, new String[]{"healer"}, new String[]{"wear"})) {
            StringBuilder itemName = new StringBuilder(words[2]);
            for (int i = 3; i < words.length; i++) {
                itemName.append(" ").append(words[i]);
            }
            turn--;
            Armor item = allArmors.get(itemName.toString());
            if (item != null)
                healer.Wear(item);
            turnCounter();
        }
        if (inputEquals(words, new String[]{"tank"}, new String[]{"wield"})) {
            StringBuilder itemName = new StringBuilder(words[2]);
            for (int i = 3; i < words.length; i++) {
                itemName.append(" ").append(words[i]);
            }
            turn--;
            Weapon item = allWeapons.get(itemName.toString());
            if (item != null)
                tank.Wield(item);
            turnCounter();
        } else if (inputEquals(words, new String[]{"healer"}, new String[]{"wield"})) {
            StringBuilder itemName = new StringBuilder(words[2]);
            for (int i = 3; i < words.length; i++) {
                itemName.append(" ").append(words[i]);
            }
            turn--;
            Weapon item = allWeapons.get(itemName.toString());
            if (item != null)
                healer.Wield(item);
            turnCounter();
        } else if (inputEquals(words, new String[]{"fighter"}, new String[]{"wield"})) {
            StringBuilder itemName = new StringBuilder(words[2]);
            for (int i = 3; i < words.length; i++) {
                itemName.append(" ").append(words[i]);
            }
            turn--;
            Weapon item = allWeapons.get(itemName.toString());
            if (item != null)
                fighter.Wield(item);
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
            fighter.DisplayStats();
        } else if (inputEquals(words, new String[]{"healer"}, displayStats)) {
            healer.DisplayStats();
        } else if (inputEquals(words, new String[]{"tank"}, displayStats)) {
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
        if (inputEquals(words, new String[]{"enemies"})){
            checkEnemies();
        }
        if (inputEquals(words, examine)) {
            StringBuilder itemName = new StringBuilder(words[1]);
            for (int i = 2; i < words.length; i++) {
                itemName.append(" ").append(words[i]);
            }
            Item item = allItems.get(itemName.toString());
            if (item != null)
                healer.Examine(item);
        }
            if (inputEquals(words, next)) {
                if (allEnemies.size() < 1) {
                    System.out.println("You are heading to the level " +n);
                    n++;
                    enemySpawner(n, allEnemies);
                    fighter.setHealth(fighter.getMaxHP());
                    tank.setHealth(tank.getMaxHP());
                    healer.setHealth(healer.getMaxHP());
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
                characters.get(index).updateHP(heal); //updateHp yazılacak
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
            } else if (healer.getHeldWeapon() instanceof Shield) {
                if (stunCooldown > 0) {
                    System.out.println("The ability is on cooldown");
                } else {
                    for (String i : allEnemies.keySet()) {

                        System.out.println("There is " + i);
                    }
                    System.out.println("Which enemy you want to stun?");

                    String enemyName = scanner.nextLine();
                    enemyName = enemyName.toLowerCase();
                    Character enemy = allEnemies.get(enemyName.toString());
                    if (enemy != null) {
                        enemy.setStunned(true);
                        System.out.println(healer.getCharClass() + " has now stunned the enemies");
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
            } else if (fighter.getHeldWeapon() instanceof Shield) {
                if (stunCooldown > 0) {
                    System.out.println("The ability is on cooldown");
                } else {
                    for (String i : allEnemies.keySet()) {

                        System.out.println("There is " + i);
                    }
                    System.out.println("Which enemy you want to stun");

                    String enemyName = scanner.nextLine();
                    enemyName = enemyName.toLowerCase();
                    Character enemy = allEnemies.get(enemyName.toString());
                    if (enemy != null) {
                        enemy.setStunned(true);
                        System.out.println(fighter.getCharClass() + " has now stunned the enemies");
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
                    for (String i : allEnemies.keySet()) {
                        System.out.println("There is " + i);
                    }
                    System.out.println("Which enemy you want to stun?");
                    String enemyName = scanner.nextLine();
                    Character enemy = allEnemies.get(enemyName.toString());
                    if (enemy != null) {
                        enemy.setStunned(true);
                        System.out.println(tank.getCharClass() + " has now stunned the enemy");
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
                    System.out.println(allEnemies.remove(enemyName.toString()));
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
                    System.out.println(allEnemies.remove(enemyName.toString()));
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
                    System.out.println(allEnemies.remove(enemyName.toString()));
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

    //enemy.getStrength() yerine enemyHeldWeapon olabilir.
    void attackPlayer(Character enemy) {
        if (!tank.getIsDead() && !tank.isInvincible() && !enemy.isStunned()) {
            if (tank.getHeldArmor().getArmorValue() >= 0) {
                if ((tank.getHeldArmor().getArmorValue() - enemy.getStrength()) < 0) {
                    int temp = Math.abs(tank.getHeldArmor().getArmorValue() - enemy.getStrength());
                    tank.getHeldArmor().setArmorValue(0);
                    tank.setHeldArmor(null);
                    System.out.println(tank.getCharClass() + "'s armor is broken");
                    tank.setHealth(tank.getHealth() - temp);
                    System.out.println(tank.getCharClass() + " also took " + temp + " damage.");
                } else {
                    tank.getHeldArmor().setArmorValue((int) (tank.getHeldArmor().getValue() - enemy.getStrength()));
                    System.out.println(tank.getCharClass() + " took " + enemy.getStrength() + " damage.");
                }
            } else {
                tank.setHealth(tank.getHealth() - enemy.getStrength());
                System.out.println(tank.getCharClass() + " took " + enemy.getStrength() + " damage.");
            }
            tank.CheckDead();
            turn = 3;
        } else if (tank.getIsDead() && !fighter.getIsDead() && !fighter.isInvincible() && !enemy.isStunned()) {
            if (fighter.getHeldArmor().getArmorValue() >= 0) {
                if ((fighter.getHeldArmor().getArmorValue()) - enemy.getStrength() < 0) {
                    int temp = Math.abs(fighter.getHeldArmor().getArmorValue() - enemy.getStrength());
                    fighter.getHeldArmor().setArmorValue(0);
                    fighter.setHeldArmor(null);
                    System.out.println(fighter.getCharClass() + "'s armor is broken");
                    fighter.setHealth(fighter.getHealth() - temp);
                    System.out.println(fighter.getCharClass() + " took " + temp + " damage.");
                } else {
                    fighter.getHeldArmor().setArmorValue((int) (fighter.getHeldArmor().getValue() - enemy.getStrength()));
                    System.out.println(fighter.getCharClass() + " took " + enemy.getStrength() + " damage.");
                }
            } else {
                fighter.setHealth(fighter.getHealth() - enemy.getStrength());
                System.out.println(fighter.getCharClass() + " took " + enemy.getStrength() + " damage.");
            }
            fighter.CheckDead();
            turn = 3;
        } else if (tank.getIsDead() && fighter.getIsDead() && !healer.getIsDead() && !healer.isInvincible() && !enemy.isStunned()) {
            if ((healer.getHeldArmor().getArmorValue()) >= 0) {
                if (healer.getHeldArmor().getArmorValue() - enemy.getStrength() < 0) {
                    int temp = Math.abs((healer.getHeldArmor().getArmorValue() - enemy.getStrength()));
                    healer.getHeldArmor().setArmorValue(0);
                    healer.setHeldArmor(null);
                    System.out.println(healer.getCharClass() + "'s armor is broken");
                    healer.setHealth(healer.getHealth() - temp);
                    System.out.println(healer.getCharClass() + " took " + temp + " damage.");
                } else {
                    healer.getHeldArmor().setArmorValue((int) (healer.getHeldArmor().getValue() - enemy.getStrength()));
                    System.out.println(healer.getCharClass() + " took " + enemy.getStrength() + " damage.");
                }
            } else {
                healer.setHealth(healer.getHealth() - enemy.getStrength());
                System.out.println(healer.getCharClass() + " took " + enemy.getStrength() + " damage.");
            }
            healer.CheckDead();
            turn = 3;
        }
        if (tank.getHealth() <= 0) {
            System.out.println(tank.getCharClass() + " died.");
            tank.setIsDead(true);
        }
        if (healer.getHealth() <= 0){
            System.out.println(healer.getCharClass() + " died.");
            healer.setIsDead(true);
        }
        if (fighter.getHealth() <= 0){
            System.out.println(fighter.getCharClass() + " died.");
            fighter.setIsDead(true);
        }
    }

    public void turnCounter() {
        System.out.println("Turns left :" + turn);
    }

    public void run() {

        //reader.close(); do not forgot
        initializeObjects();
        gameStart();
        checkEnemies();
        System.out.println(welcomeUser);
        while (running) {
            if(allEnemies.size() == 0){      //enemy armor düşürme
                Enemy enemy = new Enemy("Enemy",EnemyRandomWeapon(),EnemyRandomArmor());
                enemy.setHealth(0);
                enemy.dropRandomItem();

                System.out.println("There appeared ");

            }

        // bir de enemylerin setStunned'ını falselamak gerekiyor

            if (turn > 0) {
                System.out.println(askUser);
                String start = scan.nextLine();
                Input(start);
            } else {
                for (Character enemy : allEnemies.values()) {
                    attackPlayer(enemy);
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
            }

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