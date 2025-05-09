package cvut.fel;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpaceShip {
    private Texture texture;
    private Sprite sprite;
    private float ship_speed = 200;
    int width = Gdx.graphics.getWidth();
    private final static int WIDTH_SHIP = 50;
    private final static int HEIGHT_SHIP= 50;
    private static final String TEXTURE_NAME= "tiny_ship17.png";

    public SpaceShip(){
        this.texture = new Texture(TEXTURE_NAME);
        this.sprite = new Sprite(texture);
        this.sprite.setSize(WIDTH_SHIP, HEIGHT_SHIP);
        sprite.setPosition( (width- 50)/2, 20);
    }

    public void HandleInput(float delta){
        float newX = sprite.getX();
        float newY = sprite.getY();



        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            newX -= ship_speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            newX += ship_speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            newY += ship_speed/2 * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            newY -= ship_speed/2 * delta;
        }

        if (newX < 0 )
            newX = 0;
        if (newX > Gdx.graphics.getWidth() - WIDTH_SHIP)
            newX = Gdx.graphics.getWidth() - WIDTH_SHIP;
        if (newY < 0)
            newY = 0;
        if (newY > (Gdx.graphics.getHeight()/ 3) - HEIGHT_SHIP)
            newY = (Gdx.graphics.getHeight()/ 3) - HEIGHT_SHIP;

        sprite.setPosition(newX, newY);
    }

    public void render(SpriteBatch batch) {
        HandleInput(Gdx.graphics.getDeltaTime());
        batch.draw(texture, sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }



    public void dispose(){
        texture.dispose();
    }

}
