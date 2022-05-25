package Objects;
import java.util.ArrayList;


public class Level {
    private int enemyCount;
    //private Enemy[] enemies = new Enemy[getEnemyCount()];
    private ArrayList<Item> levelDrops = new ArrayList<Item>(); //Could this be an arraylist instead? The hardcoded limit doesn't sound very good, like what will happen to the 101st item?

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    /*public void setEnemies(Enemy[] enemies) {
        this.enemies = enemies;
    }*/

    /*public Enemy[] getEnemies() {
        return enemies;
    }*/

    public void setLevelDrops(ArrayList<Item> levelDrops) {
        this.levelDrops = levelDrops;
    }

    public ArrayList<Item> getLevelDrops() {
        return levelDrops;
    }
}
