package Objects;

public abstract class Intro {
    public static void OpeningText() throws InterruptedException{
        System.out.println("You new here?");
        Thread.sleep(1050);
        System.out.println("No problem you will get used to it");
        Thread.sleep(1050);
        System.out.println("You");
        Thread.sleep(620);
        System.out.println("are");
        Thread.sleep(620);
        System.out.println("about");
        Thread.sleep(620);
        System.out.println("to Play");
        Thread.sleep(620);
        System.out.println("""
                 _______  _______  _        _        _______  _          _______  _______  ______   ______   _______  _______\s
                (  ____ \\(  ___  )( (    /|( (    /|(  ___  )( (    /|  (  ____ \\(  ___  )(  __  \\ (  __  \\ (  ____ \\(  ____ )
                | (    \\/| (   ) ||  \\  ( ||  \\  ( || (   ) ||  \\  ( |  | (    \\/| (   ) || (  \\  )| (  \\  )| (    \\/| (    )|
                | |      | (___) ||   \\ | ||   \\ | || |   | ||   \\ | |  | (__    | |   | || |   ) || |   ) || (__    | (____)|
                | |      |  ___  || (\\ \\) || (\\ \\) || |   | || (\\ \\) |  |  __)   | |   | || |   | || |   | ||  __)   |     __)
                | |      | (   ) || | \\   || | \\   || |   | || | \\   |  | (      | |   | || |   ) || |   ) || (      | (\\ (  \s
                | (____/\\| )   ( || )  \\  || )  \\  || (___) || )  \\  |  | )      | (___) || (__/  )| (__/  )| (____/\\| ) \\ \\__
                (_______/|/     \\||/    )_)|/    )_)(_______)|/    )_)  |/       (_______)(______/ (______/ (_______/|/   \\__/
                                                                                                                             \s""");
        Thread.sleep(620);
        System.out.println("""
                Cannon Fodder is a turn-based war themed action game which was originally released in 90s for Amiga.
                This huge and beautiful code amalgamation is a re-creation of it.
                Your aim is to kill your enemies with your characters, get better items and kill more!
                Basically you write commands in order to play.
                Your character's names are fighter,healer,tank (Please type them in lowercase)
                Don't forget that item names are adjacent and all the commands are in lowercase
                You have to use commands like this\s
                examine item_name (Examine the properties of an item)
                inventory (This command prints what the characters are carrying in addition to the weapons and armor they use)
                hero_name attack enemy_name (Lets the hero you choose attack the enemy)
                quit(Quit the game)
                hero_name stats (Displays the stats of heroes)
                hero_name throw item_name (Throw an item in your inventory to the ground)
                hero_name pick item_name (Take an item on the ground into your inventory)
                hero_name wear armor_name (Wear an armor in your inventory
                hero_name wield weapon_name (Wield a weapon in your inventory)
                NEXT (Lets you get to the next level if all the enemies are dead)
                look (Lets you check the ground for available items)
                enemies (Lets you check the enemies on that level)""");
    }
                                           
    public static void Credits() throws IOException, InterruptedException {
        System.out.println("Thank you for playing!");
        Thread.sleep(720);
        System.out.println("-----------------------------");
        Thread.sleep(720);
        System.out.println("Developers :");
        Thread.sleep(720);
        System.out.println("Aykan Berk Ayvazoğlu / Main Coder & System Designer");
        Thread.sleep(720);
        System.out.println("Egemen Akgüner / Coder & General Designer ");
        Thread.sleep(720);
        System.out.println("Halil Can Parlayan / Main Coder & Code Designer");
        Thread.sleep(720);
        System.out.println(" _______  _______  _        _        _______  _          _______  _______  ______   ______   _______  _______ \n" +
                "(  ____ \\(  ___  )( (    /|( (    /|(  ___  )( (    /|  (  ____ \\(  ___  )(  __  \\ (  __  \\ (  ____ \\(  ____ )\n" +
                "| (    \\/| (   ) ||  \\  ( ||  \\  ( || (   ) ||  \\  ( |  | (    \\/| (   ) || (  \\  )| (  \\  )| (    \\/| (    )|\n" +
                "| |      | (___) ||   \\ | ||   \\ | || |   | ||   \\ | |  | (__    | |   | || |   ) || |   ) || (__    | (____)|\n" +
                "| |      |  ___  || (\\ \\) || (\\ \\) || |   | || (\\ \\) |  |  __)   | |   | || |   | || |   | ||  __)   |     __)\n" +
                "| |      | (   ) || | \\   || | \\   || |   | || | \\   |  | (      | |   | || |   ) || |   ) || (      | (\\ (   \n" +
                "| (____/\\| )   ( || )  \\  || )  \\  || (___) || )  \\  |  | )      | (___) || (__/  )| (__/  )| (____/\\| ) \\ \\__\n" +
                "(_______/|/     \\||/    )_)|/    )_)(_______)|/    )_)  |/       (_______)(______/ (______/ (_______/|/   \\__/");
        System.out.println("2022-2022 SE 116 Games , All rights reserved ®");
    }                                       
}
