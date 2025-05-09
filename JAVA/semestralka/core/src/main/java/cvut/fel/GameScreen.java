package cvut.fel;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen implements Screen {
    private final Space game;
    private SpaceShip ship;
    private Enemyspawn enemyspawn;

    public GameScreen(Space game) {
        this.game = game;
        ship = new SpaceShip();
        enemyspawn = new Enemyspawn();
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
        game.batch.end();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
}
