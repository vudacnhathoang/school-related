package cvut.fel;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameScreen implements Screen {
    private final Space game;
    private SpaceShip ship;
    private Enemyspawn enemyspawn;
    private int score;
    private final BitmapFont font;
    private final GlyphLayout layout;
    public boolean Paused = false;
    public int wave = 5;
    public int killed = 0;
    private static final float WAVE_CLEAR_TIME = 2f;
    private float waveClearedTimer = 0f;
    private boolean waveCleared = false;
    public int wavenumber = 0;

    public GameScreen(Space game) {
        this.score = 0;
        this.game = game;
        ship = new SpaceShip();
        enemyspawn = new Enemyspawn(wave);
        font = new BitmapFont();
        layout = new GlyphLayout();
    }

    public GameScreen(Space game, SaveState saveState) {
        this.score = saveState.score;
        ship = new SpaceShip();
        this.wave = saveState.wave.wave;
        this.killed = saveState.wave.killed;
        this.game = game;
        font = new BitmapFont();
        layout = new GlyphLayout();
        ship = SpaceShip.toLoad(saveState.ship);
        enemyspawn = Enemyspawn.toLoad(saveState.enemyspawn);
        this.wavenumber = saveState.wavenumber;
    }


    public void save(){
        SaveState saveState = new SaveState();
        saveState.wavenumber = wavenumber;
        saveState.wave = new SaveState.waveData(wave, killed);
        saveState.score = this.score;
        saveState.ship = ship.toSave();
        saveState.enemyspawn = enemyspawn.toSave();

        saveState.saveGame();
    }


    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        FileHandle savefile = Gdx.files.local("saves/save.json");


        if (Gdx.input.isKeyJustPressed(Input.Keys.P)){
            Paused = !Paused;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)&& Paused) {
            save();
            game.setScreen(new MenuScreen(game));
        }

        game.batch.begin();
        String scored = this.score + "";
        layout.setText(font, scored);
        font.draw(game.batch, scored, 0, Gdx.graphics.getHeight() - 10);


        if (Paused) {
            layout.setText(font, "PAUSED");
            font.draw(game.batch, "Press ESC to SAVE and EXIT", Gdx.graphics.getWidth() / 2 - layout.width / 2 - 60, Gdx.graphics.getHeight() / 2 + layout.height / 2 - 50);
            font.draw(game.batch, layout, Gdx.graphics.getWidth() / 2 - layout.width / 2, Gdx.graphics.getHeight() / 2 + layout.height / 2);
        }
        else if (waveCleared) {
            waveClearedTimer += delta;

            layout.setText(font, "WAVE "+ wavenumber +" CLEARED");
            font.draw(game.batch, layout,
                Gdx.graphics.getWidth() / 2 - layout.width / 2,
                Gdx.graphics.getHeight() / 2 + layout.height / 2);

            if (waveClearedTimer >= WAVE_CLEAR_TIME) {
                waveCleared = false;
                waveClearedTimer = 0f;


            }
        }

        else {
            ship.render(game.batch);
            enemyspawn.render(game.batch);
            List<Enemy> enemiesToRemove =new ArrayList<>();
            List<Enemy> enemiesCopy = new ArrayList<>(enemyspawn.getEnemies());
            Iterator<Enemy> enemyIterator = enemiesCopy.iterator();
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();

                Iterator<Bullet> bulletEnemyIterator = enemy.getBullets().iterator();
                while (bulletEnemyIterator.hasNext()) {
                    Bullet bullet = bulletEnemyIterator.next();
                    if(ship.sprite.getBoundingRectangle().overlaps(bullet.sprite.getBoundingRectangle())) {
                        ship.health--;
                        if (ship.health <= 0) {
                            if (savefile.exists()) {
                                savefile.delete();
                            }
                            game.setScreen(new GameOverScreen(game, score));
                        }
                        bulletEnemyIterator.remove();
                    }
                }
                Iterator<Bullet> bulletIterator = ship.getBullets().iterator();
                while (bulletIterator.hasNext()) {
                    Bullet bullet = bulletIterator.next();

                    if (enemy.sprite.getBoundingRectangle().overlaps(bullet.sprite.getBoundingRectangle())) {
                        this.score += 10;
                        enemyspawn.grid[enemy.row][enemy.col] = false;
                        enemiesToRemove.add(enemy);
                        killed ++;
                        bulletIterator.remove();
                        break;
                    }
                }
            }

            enemyspawn.getEnemies().removeAll(enemiesToRemove);
            if (killed >= wave) {
                waveCleared = true;
                wavenumber++;
                waveClearedTimer = 0f;
                enemyspawn.enemyCount = 0;
                wave += 5;
                enemyspawn.spawninterval *= 0.9f;
                if (enemyspawn.spawninterval <= 0.5f) {
                    enemyspawn.spawninterval = 0.5f;
                }
                enemyspawn.enemyLimit = wave;
                killed = 0;
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
