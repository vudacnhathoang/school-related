package cvut.fel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.random;

public class Enemyspawn {
    private static final int ROWS = 2;
    private static final int COLUMNS = 10;
    private static final int ENEMY_WIDTH = 60;
    private static final int ENEMY_HEIGHT = 60;
    private static final int SCREEN_WIDTH = Gdx.graphics.getWidth();
    private static final int SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private static final int START_Y = SCREEN_HEIGHT - ENEMY_HEIGHT - 10;
    private static final int START_X = 5;
    private float spawnTimer = 0f;
    private static final float SPAWN_INTERVAL = 3f;

    private List<Enemy> enemies;
    private boolean[][] grid;
    private int enemyCount = 0;
    private int enemyLimit;

    public Enemyspawn(int enemyLimit) {
        this.enemies = new ArrayList<>();
        this.grid = new boolean[ROWS][COLUMNS];
        this.enemyLimit = enemyLimit;

    }

    public void spawnEnemies(int enemyLimit){
        if (enemyCount < enemyLimit && enemyCount < 20) {
            int randomX = random.nextInt(10);
            int randomY = random.nextInt(2);
            while (grid[randomY][randomX] && enemyCount < 20 ){
                randomX = random.nextInt(10);
                randomY = random.nextInt(2);
            }
            grid[randomY][randomX] = true;
            enemyCount++;
            Enemy enemy = new Enemy(START_X + randomX * ENEMY_WIDTH, START_Y - randomY * ENEMY_HEIGHT);
            enemies.add(enemy);
           //  System.out.println("Enemy spawned at: " + randomY + " " + randomX);
        }
    }

    public List<Enemy> getEnemies() {

        for (Enemy enemy : enemies){
            enemy.enemyRectangle.set(enemy.sprite.getX(),enemy.sprite.getY(), enemy.sprite.getWidth(), enemy.sprite.getHeight());
        }

        return enemies;
    }


    public void render(SpriteBatch batch) {
        spawnTimer += Gdx.graphics.getDeltaTime();
        if (spawnTimer >= SPAWN_INTERVAL && enemyCount < enemyLimit) {
            spawnEnemies(enemyLimit) ;
            spawnTimer = 0f;
        }
        for (Enemy enemy : enemies) {
            enemy.render(batch);
        }
    }
}

