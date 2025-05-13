package cvut.fel;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Rectangle;

import java.util.Iterator;

public class GameScreen implements Screen {
    private final Space game;
    private SpaceShip ship;
    private Enemyspawn enemyspawn;

    public GameScreen(Space game) {
        this.game = game;
        ship = new SpaceShip();
        enemyspawn = new Enemyspawn(30);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.batch.begin();
        ship.render(game.batch);
        enemyspawn.render(game.batch);

        Iterator<Enemy> enemyIterator = enemyspawn.getEnemies().iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();

            Iterator<Bullet> bulletIterator = ship.getBullets().iterator();
            while (bulletIterator.hasNext()) {
                Bullet bullet = bulletIterator.next();

                if (enemy.enemyRectangle.overlaps(bullet.bounds)) {
                    // TO DO - add points
                    enemyIterator.remove();
                    bulletIterator.remove();
                    break;
                }
            }
        }


        game.batch.end();

    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
}
