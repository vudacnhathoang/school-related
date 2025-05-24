package cvut.fel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * LeaderboardScreen displays the leaderboard entries from a file.
 * It allows users to view the top scores and navigate back to the main menu.
 */
public class LeaderboardScreen implements Screen {
    private final Space game;
    private Stage stage;
    private Skin skin;
    private Table table;
    private List<Entry> entries = new ArrayList<>();

    public LeaderboardScreen(Space game) {
        this.game = game;
    }

    //Creating a LeaderboardScreen that displays the leaderboard entries
    @Override
    public void show() {
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table(skin);
        table.setFillParent(true);
        stage.addActor(table);

        loadEntries();
        buildTable();
    }

    /**
     * Loads leaderboard entries from a file.
     * The file should be in the format "name:score" per line.
     * Entries are sorted by score in descending order.
     */
    private void loadEntries() {
        FileHandle file = Gdx.files.local("leaderboard.txt");
        if (!file.exists()) {
           Gdx.app.error("LeaderboardScreen", "File does not exist");
        }
        if (!file.exists()) return;

        String[] lines = file.readString().split("\\r?\\n");
        for (String line : lines) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                try {
                    entries.add(new Entry(parts[0], Integer.parseInt(parts[1])));
                } catch (NumberFormatException e) {
                   Gdx.app.error("LeaderboardScreen", "Bad line in entry: " + line);
                }
            }
            entries.sort((a, b) -> Integer.compare(b.score, a.score));
        }
    }
    // Builds the table to display the leaderboard entries
    private void buildTable() {
        table.row();
        table.add(new Label("RANK", skin)).pad(10);
        table.add(new Label("NAME", skin)).pad(10);
        table.add(new Label("SCORE", skin)).pad(10);
        table.row();

        int rank = 1;
        for (Entry e : entries) {
            table.add(new Label(String.valueOf(rank++), skin)).pad(8);
            table.add(new Label(e.name, skin)).pad(8);
            table.add(new Label(String.valueOf(e.score), skin)).pad(8);
            table.row();
        }

        TextButton backBtn = new TextButton("BACK", skin);
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });

        table.row().padTop(20).colspan(3);
        table.add(backBtn).center();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(delta);
        stage.draw();
    }

    @Override public void resize(int width, int height) { stage.getViewport().update(width, height, true); }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    private static class Entry {
        final String name;
        final int score;
        Entry(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }
}
