package cvut.fel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {
    private final Space game;
    private final Stage stage;
    private final Skin skin;
    private final Json json = new Json();


    public MenuScreen(Space game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        stage.addActor(table);


        Label titleLabel = new Label("Space Invaders", skin);
        TextButton newGameBtn = new TextButton("New Game", skin);
        TextButton loadGameBtn = new TextButton("Load Game", skin);
        TextButton scoreboardBtn = new TextButton("Scoreboard", skin);

        table.add(titleLabel).pad(50).row();
        table.add(newGameBtn).pad(10).row();
        table.add(loadGameBtn).pad(10).row();
        table.add(scoreboardBtn).pad(10).row();


        newGameBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });

        loadGameBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                FileHandle savefile = Gdx.files.internal("saves/save.json");
                if (savefile.exists()) {
                    SaveState saveState = json.fromJson(SaveState.class, savefile);
                    game.setScreen(new GameScreen(game, saveState));
                } else {
                    System.out.println("No save file found.");
                }
            }
        });

        scoreboardBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(new ScoreboardScreen(game));
            }
        });
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
