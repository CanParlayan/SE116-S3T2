import Objects.Enemy;
import Objects.Level;

/*import java.security.SecureRandom;
import java.util.Scanner;

public class Map {
    public static void main(String[] args) {
        // ilkel hali //
        SecureRandom random = new SecureRandom();
        Scanner input = new Scanner(System.in);
        int n=0;
        boolean isRunning=true;

        int lev0cnt=0;
        int lev1cnt=0;
        int lev2cnt=0;
        int lev3cnt=0;
        int lev4cnt=0;


        Level level0 = new Level();
        level0.setEnemyCount((int)Math.pow(2,n));
        Level level1 = new Level();
        level1.setEnemyCount((int)Math.pow(2,n+1));
        Level level2 = new Level();
        level2.setEnemyCount((int)Math.pow(2,n+2));
        Level level3 = new Level();
        level3.setEnemyCount((int)Math.pow(2,n+3));
        Level level4 = new Level();
        level4.setEnemyCount((int)Math.pow(2,n+4));

        Enemy[] level0enemies = new Enemy[level0.getEnemyCount()];
        Enemy[] level1enemies = new Enemy[level1.getEnemyCount()];
        Enemy[] level2enemies = new Enemy[level2.getEnemyCount()];
        Enemy[] level3enemies = new Enemy[level3.getEnemyCount()];
        Enemy[] level4enemies = new Enemy[level4.getEnemyCount()];

        for(int i=0;i<Math.pow(2,n);i++){
            Enemy newEnemy = new Enemy(random.nextInt(5)+1,random.nextInt(5)+1,random.nextInt(5)+1,20);
            level0enemies[i] = newEnemy;
        }
        level0.setEnemies(level0enemies);

        for(int i=0;i<Math.pow(2,n+1);i++){
            Enemy newEnemy = new Enemy(random.nextInt(5)+1,random.nextInt(5)+1,random.nextInt(5)+1,random.nextInt(10));
            level1enemies[i] = newEnemy;
        }
        level1.setEnemies(level1enemies);

        for(int i=0;i<Math.pow(2,n+2);i++){
            Enemy newEnemy = new Enemy(random.nextInt(5)+1,random.nextInt(5)+1,random.nextInt(5)+1,random.nextInt(10));
            level2enemies[i] = newEnemy;
        }
        level2.setEnemies(level2enemies);

        for(int i=0;i<Math.pow(2,n+3);i++){
            Enemy newEnemy = new Enemy(random.nextInt(5)+1,random.nextInt(5)+1,random.nextInt(5)+1,random.nextInt(10));
            level3enemies[i] = newEnemy;
        }
        level3.setEnemies(level3enemies);

        for(int i=0;i<Math.pow(2,n+4);i++){
            Enemy newEnemy = new Enemy(random.nextInt(5)+1,random.nextInt(5)+1,random.nextInt(5)+1,random.nextInt(10));
            level4enemies[i] = newEnemy;
        }
        level4.setEnemies(level4enemies);

        System.out.println(" _____   ___   _   _  _   _ _____ _   _  ______ _________________ ___________ \n" +
                " *    /  __ \\ / _ \\ | \\ | || \\ | |  _  | \\ | | |  ___|  _  |  _  \\  _  \\  ___| ___ \\\n" +
                " *    | /  \\// /_\\ \\|  \\| ||  \\| | | | |  \\| | | |_  | | | | | | | | | | |__ | |_/ /\n" +
                " *    | |    |  _  || . ` || . ` | | | | . ` | |  _| | | | | | | | | | |  __||    / \n" +
                " *    | \\__/\\| | | || |\\  || |\\  \\ \\_/ / |\\  | | |   \\ \\_/ / |/ /| |/ /| |___| |\\ \\ \n" +
                " *     \\____/\\_| |_/\\_| \\_/\\_| \\_/\\___/\\_| \\_/ \\_|    \\___/|___/ |___/ \\____/\\_| \\_|");

        String command = "null";

        while (lev0cnt != level0.getEnemyCount()){
            int x=0;
            command = input.next();
            Enemy currentEnemy = level0.getEnemies()[x];
            if(command.equals("next")){
                // next demek yerine karakter komutu çekilecek //
                level0.getEnemies()[x].setHp(level0.getEnemies()[x].getHp()-10);
            }
            if(currentEnemy.getHp() <= 0){
                x++;
                lev0cnt++;
            }
            System.out.println("enemy health is "+currentEnemy.getHp());
            System.out.println("level is 0");
        }

        while (lev1cnt != level1.getEnemyCount()){
            command = input.next();
            if(command.equals("next")){
                lev1cnt++;
            }
            System.out.println("enemy killed");
            System.out.println("level is 1");
        }

    }
}
 */