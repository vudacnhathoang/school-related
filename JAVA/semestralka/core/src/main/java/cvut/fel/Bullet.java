package cvut.fel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {

    final static int width = 10;
    final static int height = 10;

    private Texture texture;
    public Sprite sprite;
    public Rectangle bounds;



    public Bullet(String texture, int x, int y){
        this.texture = new Texture(texture);
        this.sprite = new Sprite(this.texture);
        this.sprite.setSize(width, height);
        this.sprite.setPosition(x, y);
        this.bounds = new Rectangle();
    }

    public void move(float delta, int speed){
        sprite.setPosition(sprite.getX(), sprite.getY() + speed *delta);

    }

    public void render(SpriteBatch batch){
        batch.draw(texture, sprite.getX(), sprite.getY(), width, height);

    }

    public void dispose(){
        // TODO
    }


}
