package cvut.fel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.random;

public class Enemyspawn {
    private static final int ROWS = 2;
    private static final int COLUMNS = 10;
    private static final int ENEMY_WIDTH = 60;
    private static final int ENEMY_HEIGHT = 60;
    private static final int SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private static final int START_Y = SCREEN_HEIGHT - ENEMY_HEIGHT - 40;
    private static final int START_X = 5;
    private float spawnTimer = 0f;
    public float spawninterval = 3f;
    public List<Enemy> enemies;
    public boolean[][] grid;
    public int enemyCount = 0;
    public int enemyLimit;
    public String enemySkin;
    public int bulletSpeed;
    public TimeManager timer;

    public Enemyspawn(int enemyLimit, float spawninterval, String enemySkin, int bulletSpeed, TimeManager timer) {
        this.enemies = new ArrayList<>();
        this.grid = new boolean[ROWS][COLUMNS];
        this.enemyLimit = enemyLimit;
        this.spawninterval = spawninterval;
        this.enemySkin = enemySkin;
        this.timer = timer;
        this.bulletSpeed = bulletSpeed;
    }

    public void spawnEnemies(int enemyLimit) {
        if (enemyCount < enemyLimit && enemyCount < 20) {
            int randomX = random.nextInt(10);
            int randomY = random.nextInt(2);
            while (grid[randomY][randomX] && enemyCount < 20) {
                randomX = random.nextInt(10);
                randomY = random.nextInt(2);
            }
            grid[randomY][randomX] = true;
            enemyCount++;
            Enemy enemy = new Enemy(START_X + randomX * ENEMY_WIDTH, START_Y - randomY * ENEMY_HEIGHT, randomY, randomX, enemySkin, bulletSpeed);
            enemies.add(enemy);
            Gdx.app.log("Enemyspawn " + timer.getCurrentTime(), "spawned enemy at " + enemy.sprite.getX() + ", " + enemy.sprite.getY());
        }
    }

    public SaveState.SpawnerData toSave() {
        SaveState.SpawnerData spawnerData = new SaveState.SpawnerData();
        spawnerData.spawnrate = spawninterval;
        spawnerData.enemyCount = enemyCount;
        spawnerData.enemyLimit = enemyLimit;
        spawnerData.grid = grid;
        spawnerData.enemies = new ArrayList<>();
        for (Enemy enemy : enemies) {
            spawnerData.enemies.add(enemy.toSave());
        }
        return spawnerData;
    }

    public static Enemyspawn toLoad(SaveState.SpawnerData data, SaveState.ModeData mode) {
        Enemyspawn enemySpawn = new Enemyspawn(data.enemyLimit, mode.spawnRate, mode.enemySkin, mode.bulletSpeed, null);
        enemySpawn.enemyCount = data.enemyCount;
        enemySpawn.spawninterval = data.spawnrate;
        enemySpawn.grid = data.grid;
        for (SaveState.EnemyData enemyData : data.enemies) {
            Enemy enemy = Enemy.toLoad(enemyData, mode.enemySkin, mode.bulletSpeed);
            enemySpawn.enemies.add(enemy);
        }
        return enemySpawn;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }


    public void render(SpriteBatch batch) {
        spawnTimer += Gdx.graphics.getDeltaTime();
        if (spawnTimer >= spawninterval && enemyCount < enemyLimit) {
            spawnEnemies(enemyLimit);
            spawnTimer = 0f;
        }
        for (Enemy enemy : enemies) {
            enemy.render(batch);
        }
    }

    public void dispose() {
        for (Enemy enemy : enemies) {
            enemy.dispose();
        }
        enemies.clear();
    }
}
