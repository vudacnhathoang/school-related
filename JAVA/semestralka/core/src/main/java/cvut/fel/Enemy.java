package cvut.fel;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Enemy {
    private static final String TEXTURE_NAME= "enemy.png";
    int width = 50;
    int height = 50;
    int x;
    int y;
    private Texture texture;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void create(){
        texture = new Texture(TEXTURE_NAME);
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, x, y, width, height);
    }

    public void enemy_shoot(){
        // TODO
    }

    public void dispose(){
        texture.dispose();
    }


}
