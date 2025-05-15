package cvut.fel;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class GameOverScreen implements Screen {
    private final Space game;
    private final BitmapFont font;
    private final GlyphLayout layout;
    private final int finalScore;


    public GameOverScreen(Space game, int finalScore) {
        this.game = game;
        this.font = new BitmapFont();
        this.layout = new GlyphLayout();
        this.finalScore = finalScore;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        layout.setText(font, "GAME OVER");
        font.draw(game.batch, layout,
            Gdx.graphics.getWidth() / 2 - layout.width / 2,
            Gdx.graphics.getHeight() / 2 + layout.height / 2 + 30);

        layout.setText(font, "Score: " + finalScore);
        font.draw(game.batch, layout,
            Gdx.graphics.getWidth() / 2 - layout.width / 2,
            Gdx.graphics.getHeight() / 2 + layout.height / 2);

        layout.setText(font, "Press SPACE to return to Menu");
        font.draw(game.batch, layout,
            Gdx.graphics.getWidth() / 2 - layout.width / 2,
            Gdx.graphics.getHeight() / 2 - layout.height / 2 - 30);

        game.batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            game.setScreen(new MenuScreen(game));
        }
    }
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        font.dispose();
    }

}
