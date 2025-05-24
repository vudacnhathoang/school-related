// core/src/main/java/cvut/fel/GameOverScreen.java
package cvut.fel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.InputEvent;


/**
 * GameOverScreen is displayed when the game ends.
 * It allows the player to enter their name and submit their score to a leaderboard.
 */
public class GameOverScreen implements Screen {
    private final Space game;
    private final int finalScore;
    private Stage stage;
    private Skin skin;
    private Table table;
    private TextField nameField;
    private TextButton submitBtn;
    private TextButton backBtn;

    public GameOverScreen(Space game, int finalScore) {
        this.game = game;
        this.finalScore = finalScore;
    }

    //Creating a simple entry class to hold name and score
    @Override
    public void show() {
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table(skin);
        table.setFillParent(true);
        stage.addActor(table);

        Label title = new Label("GAME OVER", skin);
        Label scoreLabel = new Label("Score: " + finalScore, skin);
        Label prompt = new Label("Enter your name:", skin);
        nameField = new TextField("", skin);
        submitBtn = new TextButton("SUBMIT", skin);
        backBtn = new TextButton("BACK", skin);

        submitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String name = nameField.getText().trim();
                if (!name.isEmpty()) {
                    FileHandle file = Gdx.files.local("leaderboard.txt");
                    file.writeString(name + ":" + finalScore + "\n", true);
                    submitBtn.setDisabled(true);
                    nameField.setDisabled(true);
                }
                game.setScreen(new MenuScreen(game));
            }
        });

        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });

        table.add(title).pad(10).row();
        table.add(scoreLabel).pad(10).row();
        table.add(prompt).pad(5).row();
        table.add(nameField).width(200).pad(5).row();
        table.add(submitBtn).pad(5).row();
        table.add(backBtn).pad(5);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
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
