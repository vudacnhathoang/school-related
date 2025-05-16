package cvut.fel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {
    private final Space game;
    private final Stage stage;
    private final Skin skin;
    private final Json json = new Json();

    public static class GameMode {
        public String enemySkin;
        public float spawnRate;
        public int bulletSpeed;

        public GameMode(){}
    }



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

        CheckBox easyRadio = new CheckBox("Easy", skin, "radio");
        CheckBox mediumRadio = new CheckBox("Medium", skin, "radio");
        CheckBox hardRadio = new CheckBox("Hard", skin, "radio");

        ButtonGroup<CheckBox> radioGroup = new ButtonGroup<>(easyRadio, mediumRadio, hardRadio);
        radioGroup.setMinCheckCount(1);     // Always one selected
        radioGroup.setMaxCheckCount(1);     // Only one can be selected
        radioGroup.setUncheckLast(true);    // Cannot unselect all
        easyRadio.setChecked(true);


        table.add(titleLabel).pad(50).row();
        table.add(newGameBtn).pad(10).row();
        table.add(loadGameBtn).pad(10).row();
        table.add(scoreboardBtn).pad(10).row();

        table.add(new Label("Select Difficulty:", skin)).padBottom(10).row();
        table.add(easyRadio).left().padBottom(5).row();
        table.add(mediumRadio).left().padBottom(5).row();
        table.add(hardRadio).left().padBottom(20).row();


        newGameBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                String selectedFile = null;
                if (easyRadio.isChecked()) selectedFile = "modes/easy.json";
                else if (mediumRadio.isChecked()) selectedFile = "modes/medium.json";
                else if (hardRadio.isChecked()) selectedFile = "modes/hard.json";

                Json json = new Json();
                GameMode mode = json.fromJson(GameMode.class, Gdx.files.internal(selectedFile));
                System.out.println("Selected mode: " + mode.enemySkin + ", " + mode.spawnRate + ", " + mode.bulletSpeed);
                game.setScreen(new GameScreen(game, mode));
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
                game.setScreen(new LeaderboardScreen(game));
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
