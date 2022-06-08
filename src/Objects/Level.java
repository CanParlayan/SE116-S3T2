package Objects;
import java.util.ArrayList;


public class Level {
    private int enemyCount;
    //private Enemy[] enemies = new Enemy[getEnemyCount()];
    private ArrayList<Item> levelDrops = new ArrayList<>(); //Could this be an arraylist instead? The hardcoded limit doesn't sound very good, like what will happen to the 101st item?
    public void AddToLevelDrops(Item item) { //encapsulation friendly functions to add/remove to the ground
        this.levelDrops.add(item);
    }

    public void RemoveFromLevelDrops(Item item) {
        this.levelDrops.remove(item);
    }
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

    public void EmptyLevelDrops(){
        this.levelDrops.clear();
    }
}
