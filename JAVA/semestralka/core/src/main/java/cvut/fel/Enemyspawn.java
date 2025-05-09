package cvut.fel;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import cvut.fel.Enemy;

import java.util.ArrayList;
import java.util.List;

public class Enemyspawn {
    int WIDTH_SQAURE = Gdx.graphics.getWidth()/4;
    int HEIGHT_SQAURE = Gdx.graphics.getWidth()/4;
    int[][] array_enemies;
    long time;
    long delay = 5000L;
    List<Enemy> enemies;


    public Enemyspawn() {
        this.array_enemies = new int[2][4];
        enemies = new ArrayList<>();
    }

    public void create(){
        time = System.currentTimeMillis();
    }

    public void render(SpriteBatch batch){
        time += Gdx.graphics.getDeltaTime() * 1000;
        if (time > delay) {
            time = 0;
            spawn_enemy(batch);
        }
        for (Enemy enemy : enemies){
            enemy.render(batch);
        }
    }

    private void spawn_enemy(SpriteBatch batch){
        int row = (int) (Math.random() * 2);
        int column = (int) (Math.random() * 4);

        if (array_enemies[row][column] == 0) {
            array_enemies[row][column] = 1;
            Enemy enemy = new Enemy( WIDTH_SQAURE * column  + 30, WIDTH_SQAURE * row )  ;
            enemy.create();
            System.out.println("Created a enemy on " + row + " " + column);
            enemy.render(batch);
            enemies.add(enemy);
        }
    };
}

