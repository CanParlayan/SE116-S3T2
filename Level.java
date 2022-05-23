public class Level {
    private int enemyCount;
    private Enemy[] enemies = new Enemy[getEnemyCount()];
    private Item[] levelDrops = new Item[100];

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public void setEnemies(Enemy[] enemies) {
        this.enemies = enemies;
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public void setLevelDrops(Item[] levelDrops) {
        this.levelDrops = levelDrops;
    }

    public Item[] getLevelDrops() {
        return levelDrops;
    }
}
