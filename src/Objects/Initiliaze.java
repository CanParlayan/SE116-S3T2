package Objects;

import Materials.*;
import Items.*;
import Items.Wand;

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
            examine = {"examine"}, specialAction = {"special"};
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
    static Character tank = new Character("Tank", level1, mithrilShield);
    static Character healer = new Character("Healer", level1, mithrilWand);
    static Character fighter = new Character("Fighter", level1, mithrilSword);

    public static ArrayList<Character> characters = new ArrayList<>();

    static {
        characters.add(tank);
        characters.add(healer);
        characters.add(fighter);
    }

    public static HashMap<String, Weapon> allWeapons = new HashMap<>();
    public static HashMap<String, Armor> allArmors = new HashMap<>();
    public static HashMap<String, Item> allItems = new HashMap<>();
    public static HashMap<String, Sword> allSwords = new HashMap<>(); //CanParlayan: make sure to initialize
    public static HashMap<String, Shield> allShields = new HashMap<>();
    public static HashMap<String, Wand> allWands = new HashMap<>();

    public static Weapon EnemyRandomWeapon(){ //probably very unbalanced gameplay wise because every material gets the same weight, but well, adds randomness into runs
        Random random = new Random();
        int swordDropChance = 80;
        int shieldDropChance = 10;
        int wandDropChance = 10;
        int randomNumber = random.nextInt((swordDropChance+shieldDropChance+wandDropChance+1));
        if (randomNumber < swordDropChance){
            List<String> swordsAsList = new ArrayList<String>(allSwords.keySet()); //since the hashmaps are keyed in strings, I just throw all keys into an arraylist and randomize over it. Learnt this just now.
            return allSwords.get(swordsAsList.get(random.nextInt(swordsAsList.size())));
        }
        else if (randomNumber < (swordDropChance+shieldDropChance)){
            List<String> shieldsAsList = new ArrayList<String>(allShields.keySet());
            return allSwords.get(shieldsAsList.get(random.nextInt(shieldsAsList.size())));
        }
        else if (randomNumber <= (swordDropChance+shieldDropChance+wandDropChance)){
            List<String> wandsAsList = new ArrayList<String>(allWands.keySet());
            return allSwords.get(wandsAsList.get(random.nextInt(wandsAsList.size())));
        }
        return null; //it drops nothing in the event above code fails to work
    }
    public static Armor EnemyRandomArmor(){
        Random random = new Random();
        List<String> armorsAsList = new ArrayList<String>(allArmors.keySet());
        return allArmors.get(armorsAsList.get(random.nextInt(armorsAsList.size())));
    }
    private void initializeItems() {
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
        Character enemy = new Character("Enemy", level1, EnemyRandomWeapon());
        allEnemies.put("enemy", enemy);
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
/*
    public static void enemySpawner //aykan'ın kodu kullanılacak
    }*/

    private void Input(String input) {

        input = input.replaceAll("\\s+", " ");


        input = input.trim();


        String[] words = input.split(" ");


        if (words.length == 0) {
            return;
        }
        if (inputEquals(words, new String[]{"tank"}, new String[]{"throw"})) {
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
                turn--;
            }
            Item item = allItems.get(itemName);
            if (item != null)
                tank.Throw(item);
        } else if (inputEquals(words, new String[]{"healer"}, new String[]{"throw"})) {
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
            }
            Item item = allItems.get(itemName);
            if (item != null)
                healer.Throw(item);
        } else if (inputEquals(words, new String[]{"fighter"}, new String[]{"throw"})) {
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
            }
            turn--;
            Item item = allItems.get(itemName);
            if (item != null)
                fighter.Throw(item);
            turnCounter();
        }
        if (inputEquals(words, new String[]{"tank"}, new String[]{"pick"})) {
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
            }
            Item item = allItems.get(itemName);
            if (item != null)
                tank.Pick(item);
            level1.RemoveFromLevelDrops(item);
        } else if (inputEquals(words, new String[]{"fighter"}, new String[]{"pick"})) {
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
            }
            turn--;
            Item item = allItems.get(itemName);
            if (item != null)
                fighter.Pick(item);
            level1.RemoveFromLevelDrops(item);
            turnCounter();
        } else if (inputEquals(words, new String[]{"healer"}, new String[]{"pick"})) {
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
            }
            Item item = allItems.get(itemName);
            if (item != null)
                healer.Pick(item);
            level1.RemoveFromLevelDrops(item);
        }
        if (inputEquals(words, new String[]{"tank"}, new String[]{"wear"})) {
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
            }
            Armor item = allArmors.get(itemName);
            if (item != null)
                tank.Wear(item);
        } else if (inputEquals(words, new String[]{"fighter"}, new String[]{"wear"})) {
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
            }
            Armor item = allArmors.get(itemName);
            if (item != null)
                fighter.Wear(item);
        } else if (inputEquals(words, new String[]{"healer"}, new String[]{"wear"})) {
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
            }
            Armor item = allArmors.get(itemName);
            if (item != null)
                healer.Wear(item);
        }
        if (inputEquals(words, new String[]{"tank"}, new String[]{"wield"})) {
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
            }
            Weapon item = allWeapons.get(itemName);
            if (item != null)
                tank.Wield(item);
        } else if (inputEquals(words, new String[]{"healer"}, new String[]{"wield"})) {
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
            }
            Weapon item = allWeapons.get(itemName);
            if (item != null)
                healer.Wield(item);
        } else if (inputEquals(words, new String[]{"fighter"}, new String[]{"wield"})) {
            String itemName = words[2];
            for (int i = 3; i < words.length; i++) {
                itemName += " " + words[i];
            }
            turn--;
            Weapon item = allWeapons.get(itemName);
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
        if (inputEquals(words, examine)) {
            String itemName = words[1];
            for (int i = 2; i < words.length; i++) {
                itemName += " " + words[i];
            }
            turn--;
            Item item = allItems.get(itemName);
            if (item != null)
                healer.Examine(item);
            turnCounter();
        }
        if (inputEquals(words, specialAction)) {       //special action later to be changed
            Scanner scanner = new Scanner(System.in);
            if (healer.getHeldWeapon() instanceof Wand) {
                System.out.println("Which character you want to heal?");
                for (int i = 0; i < characters.size(); i++) {
                    System.out.println(characters.get(i).getCharClass() + " has " + characters.get(i).getHealth());
                }
                String specialInput = scanner.nextLine();
                specialInput = specialInput.toLowerCase();
                System.out.println(specialInput);
                int index = 0;
                for (int i = 0; i < characters.size(); i++) {
                    if (characters.get(i).getCharClass().toLowerCase().equals(specialInput)) {
                        break;
                    }
                    index++;
                }
                int heal = (int) ((Wand) healer.getHeldWeapon()).heal(fighter);
                System.out.println(heal);
                characters.get(index).updateHP(heal); //updateHp yazılacak
                System.out.println("MaxHp" + characters.get(index).getMaxHP());
                characters.get(index).setHealth(characters.get(index).getHealth() + heal);
                System.out.println("Updated HP of " + characters.get(index).getCharClass() + " is " + characters.get(index).getHealth());
                turn++;
            } /*else if (fighter.getHeldWeapon().getType().equals("Sword")) {
                System.out.println("You can either stay away or block an enemy for one turn.");
                System.out.println("To stay away press 1, to block press 2");
                int specialInput = scanner.nextInt();
                scanner.nextLine();
                switch (specialInput) {
                    case 1:
                        Sword swordOfFighter = (Sword) fighter.getHeldWeapon();
                        int stayAway = (int) swordOfFighter.disengage(fighter);
                        fighter.setStayAway(stayAway);
                        break;
                    case 2:
                        int enemyCount = enemies.size();
                        enemyCount = enemyCount - 1;
                        int randEnemy = rand.nextInt(0, enemyCount);
                        enemies.get(randEnemy).setBlock(true);
                        System.out.println(enemies.get(randEnemy).getName() + " has been blocked for one turn.");
                        turn++;
                        break;
                    default:
                        System.out.println("Please enter a valid integer.");
                        break;
                }*/
      /*  } else if (fighter.getHeldWeapon().getType().equals("Shield")) {
            System.out.println("The special action of the shield is only activated if and only if when an enemy attacks.");
        } else {
            System.out.println("Since Fighter has no weapon, she has no special action too.");
        */
        }
        if (inputEquals(words, new String[]{"fighter"}, attack)) {
                String enemyName = words[2];
                for (int i = 3; i < words.length; i++) {
                    enemyName += " " + words[i];
                }
                Character enemy = allEnemies.get(enemyName);
                if (enemy != null) {
                    int fighterDamage = fighter.CalculateDamage();
                    enemy.TakeDamage(fighterDamage);
                    allEnemies.remove("enemy");
                }
                turn--;
                turnCounter();
            }
        if(inputEquals(words, new String[]{"check"})){
            checkEnemies();
        }


           /* while (enemies.size() > 0) {
                int i = 0; //If a character attacks or wield/wears an item than turn count increases one and when the turn count reaches 3(2) while loop will terminate
                for (int a = 0; a < enemies.size(); a++) {
                    if (enemies.get(a).isBlock()) {
                        enemies.get(a).setBlock(false);
                    }
                }
                for (int bei = 0; bei < myCharacters.size(); bei++) {
                    if (myCharacters.get(bei).getStayAway() > 0) {
                        int fung = myCharacters.get(bei).getStayAway() - 1;
                        myCharacters.get(bei).setStayAway(fung);
                    }
                }
                while (i < 3) {
                    if (enemies.size() > 0) {
                                    String choice = sc.nextLine();
                                    choice = choice.toLowerCase();
                                    switch (choice) {
                                        case "attack":
                                            System.out.println("Which enemy you want to attack?");
                                            //Enemy list
                                            for (int b = 0; b < enemies.size(); b++) {
                                                System.out.println("Name: " + enemies.get(b).getName() + "\nHP: " + enemies.get(b).getHP());
                                            }
                                            //Inputting
                                            System.out.println("Please type their name: ");
                                            String enemyChoice = sc.nextLine();
                                            enemyChoice = enemyChoice.toLowerCase();
                                            //Searching the index of the desired enemy.
                                            int index1 = 0;
                                            for (int a = 0; a < enemies.size(); a++) {
                                                if (enemies.get(a).getName().toLowerCase().equals(enemyChoice)) {
                                                    break;
                                                }
                                                index1++;
                                            }
                                            System.out.println("Luna is attacking to " + enemies.get(index1).getName());
                                            double damage = healer.damage();
                                            enemies.get(index1).updateHP(1, damage);
                                            System.out.println("Luna has attacked with " + damage + ".");
                                            System.out.println(enemies.get(index1).getName() + " has " + enemies.get(index1).getHP() + " HP.");
                                            if (enemies.get(index1).getHP() <= 0) {
                                                System.out.println(enemies.get(index1).getName() + " is dead.");
                                                Weapons newWeapon = dropWeapon();
                                                System.out.println(newWeapon.getName() + " has been dropped.");
                                                levelItems.add(newWeapon);
                                                enemies.remove(index1);
                                            }
                                            if (enemies.size() > 0) {
                                                System.out.println("You have used 1 turn for this action. Remaining turns: " + (3 - (i + 1)));
                                            }
                                            i++;
                                            break;
                                    }
                                }
                        }
                    }
            if (enemies.size() > 0) {
                for (int xue = 0; xue < enemies.size(); xue++) {
                    int hua = myCharacters.size();
                    hua = hua - 1;
                    int randomXue = rand.nextInt(0, hua);
                    if (!enemies.get(xue).isBlock()) {
                        if (myCharacters.get(randomXue).getStayAway() == 0) {
                            if (myCharacters.get(randomXue).getWieldedWeapon().getType().equals("Shield")) {
                                boolean isBlock = rand.nextBoolean();
                                if (!isBlock) {
                                    System.out.println(enemies.get(xue).getName() + " is attacking to " + myCharacters.get(randomXue).getName() + " with a damage " + enemies.get(xue).damage());
                                    double damage = enemies.get(xue).damage();
                                    myCharacters.get(randomXue).updateHP(1, damage);
                                    System.out.println("Remaining HP of " + myCharacters.get(randomXue).getHP());
                                } else {
                                    System.out.println(enemies.get(xue));
                                }
                            } else {
                                System.out.println(enemies.get(xue).getName() + " is attacking to " + myCharacters.get(randomXue).getName() + " with a damage " + enemies.get(xue).damage());
                                double damage = enemies.get(xue).damage();
                                myCharacters.get(randomXue).updateHP(1, damage);
                                System.out.println("Remaining HP of " + myCharacters.get(randomXue).getHP());
                            }
                        }
                        if (myCharacters.get(randomXue).getHP() <= 0) {
                            System.out.println(myCharacters.get(randomXue).getName() + " is dead T-T");
                            myCharacters.remove(randomXue);
                        }
                    } else {
                        System.out.println(enemies.get(xue).getName() + " is not able to move for one turn.");
                    }
                }
            } else {
                System.out.println("You are going to level up. ");
                i = 3;
            }else{
                gameFlag = false;
            }

            level++;
            enemyAmount = (int) Math.pow(2, level);
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
    public void checkEnemies() {
        if (allEnemies.size() < 1) //delete later
            System.out.println("No enemy around");
        else {
            for (String i : allEnemies.keySet()) {

                System.out.println("There is " + i);
            }
        }
    }
    void attackPlayer(Character enemy, Character character) {
        if (character == tank && !tank.getIsDead()) {
            if (character.getHeldArmor().getArmorValue() >= 0) {
                if (character.getHeldArmor().getArmorValue() - enemy.getStrength() < 0) {
                    int temp = Math.abs((int) (character.getHeldArmor().getArmorValue() - enemy.getStrength()));
                    character.getHeldArmor().setArmorValue(0);
                    character.setHealth(character.getHealth() - temp);
                } else {
                    character.getHeldArmor().setArmorValue(character.getHeldArmor().getValue() - enemy.getStrength());
                }
            } else {
                character.setHealth(character.getHealth() - enemy.getStrength());
            }
            turn = 3;
        }
        else if (character == tank && tank.getIsDead() && !fighter.getIsDead()) {
            character = fighter;
            if (character.getHeldArmor().getArmorValue() >= 0) {
                if (character.getHeldArmor().getArmorValue() - enemy.getStrength() < 0) {
                    int temp = Math.abs((int) (character.getHeldArmor().getArmorValue() - enemy.getStrength()));
                    character.getHeldArmor().setArmorValue(0);
                    character.setHealth(character.getHealth() - temp);
                } else {
                    character.getHeldArmor().setArmorValue(character.getHeldArmor().getValue() - enemy.getStrength());
                }
            } else {
                character.setHealth(character.getHealth() - enemy.getStrength());
            }
            turn = 3;
        }
        else if (character == tank && tank.getIsDead() && fighter.getIsDead() && !healer.getIsDead()) {
            character = healer;
            if (character.getHeldArmor().getArmorValue() >= 0) {
                if (character.getHeldArmor().getArmorValue() - enemy.getStrength() < 0) {
                    int temp = Math.abs((int) (character.getHeldArmor().getArmorValue() - enemy.getStrength()));
                    character.getHeldArmor().setArmorValue(0);
                    character.setHealth(character.getHealth() - temp);
                } else {
                    character.getHeldArmor().setArmorValue(character.getHeldArmor().getValue() - enemy.getStrength());
                }
            } else {
                character.setHealth(character.getHealth() - enemy.getStrength());
            }
            turn = 3;
        }
        else if (character == fighter && !fighter.getIsDead()){
            if (character.getHeldArmor().getArmorValue() >= 0) {
                if (character.getHeldArmor().getArmorValue() - enemy.getStrength() < 0) {
                    int temp = Math.abs((int) (character.getHeldArmor().getArmorValue() - enemy.getStrength()));
                    character.getHeldArmor().setArmorValue(0);
                    character.setHealth(character.getHealth() - temp);
                } else {
                    character.getHeldArmor().setArmorValue(character.getHeldArmor().getValue() - enemy.getStrength());
                }
            } else {
                character.setHealth(character.getHealth() - enemy.getStrength());
            }
            turn = 3;
        }
        else if (character == fighter && fighter.getIsDead() && !healer.getIsDead()) {
            character = healer;
            if (character.getHeldArmor().getArmorValue() >= 0) {
                if (character.getHeldArmor().getArmorValue() - enemy.getStrength() < 0) {
                    int temp = Math.abs((int) (character.getHeldArmor().getArmorValue() - enemy.getStrength()));
                    character.getHeldArmor().setArmorValue(0);
                    character.setHealth(character.getHealth() - temp);
                } else {
                    character.getHeldArmor().setArmorValue(character.getHeldArmor().getValue() - enemy.getStrength());
                }
            } else {
                character.setHealth(character.getHealth() - enemy.getStrength());
            }
            turn = 3;
        }
        else if (character == healer && !healer.getIsDead()) {
            if (character.getHeldArmor().getArmorValue() >= 0) {
                if (character.getHeldArmor().getArmorValue() - enemy.getStrength() < 0) {
                    int temp = Math.abs((int) (character.getHeldArmor().getArmorValue() - enemy.getStrength()));
                    character.getHeldArmor().setArmorValue(0);
                    character.setHealth(character.getHealth() - temp);
                } else {
                    character.getHeldArmor().setArmorValue(character.getHeldArmor().getValue() - enemy.getStrength());
                }
            } else {
                character.setHealth(character.getHealth() - enemy.getStrength());
            }
            turn = 3;
        }
    }
    public void turnCounter(){
        System.out.println("Turns left :" + turn);
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
        if(turn < 1){
           // attackPlayer(,fighter);
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