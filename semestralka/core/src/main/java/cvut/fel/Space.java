package cvut.fel;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Space extends Game {
    public SpriteBatch batch;


    @Override
    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new MenuScreen(this)); // Show the menu screen first
    }



    @Override
    public void dispose() {
        super.dispose();
        if (batch != null) {
            batch.dispose();
        }
        Screen screen = getScreen();
        if (screen != null) {
            screen.dispose();
        }

        if (getScreen() instanceof GameScreen) {

            GameScreen gameScreen = (GameScreen) getScreen();
            gameScreen.dispose();

        }
        if (getScreen() instanceof MenuScreen) {
            MenuScreen menuScreen = (MenuScreen) getScreen();
            menuScreen.dispose();
        }
        if (getScreen() instanceof GameOverScreen) {
            GameOverScreen gameOverScreen = (GameOverScreen) getScreen();
            gameOverScreen.dispose();
        }

        if (getScreen() instanceof LeaderboardScreen) {
            LeaderboardScreen leaderboardScreen = (LeaderboardScreen) getScreen();
            leaderboardScreen.dispose();
        }


    }
}
