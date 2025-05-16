package cvut.fel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import cvut.fel.SaveState.BulletData;

public class Bullet {

    final static int width = 10;
    final static int height = 10;

    private transient Texture texture;
    public transient Sprite sprite;



    public Bullet(String texture, int x, int y){
        this.texture = new Texture(texture);
        this.sprite = new Sprite(this.texture);
        this.sprite.setSize(width, height);
        this.sprite.setPosition(x, y);
    }

    public BulletData toSave(String texturename){
        BulletData data = new BulletData();
        data.x = sprite.getX();
        data.y = sprite.getY();
        data.TextureName = texturename;
        return data;
    }

    public static Bullet toLoad(BulletData data){
        Bullet bullet = new Bullet(data.TextureName, (int)data.x, (int)data.y);
        return bullet;
    }


    public void move(float delta, int speed){
        sprite.setPosition(sprite.getX(), sprite.getY() + speed *delta);

    }

    public void render(SpriteBatch batch){
        batch.draw(texture, sprite.getX(), sprite.getY(), width, height);

    }

    public void dispose(){
        texture.dispose();
        sprite.getTexture().dispose();

    }


}
