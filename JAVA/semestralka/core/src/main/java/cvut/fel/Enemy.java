package cvut.fel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;



public class Enemy {
    private static final String TEXTURE_NAME= "enemy.png";
    public Sprite sprite;
    private Texture texture;
    private List<Bullet> bullets;
    private int bullet_speed = -300;
    private float SpawnTimer = 0f;
    private float lastFireTime = 0;
    private static final float FIRE_RATE = 2f;
    private static final String BULLET_NAME= "laser_red.png";
    public Rectangle enemyRectangle;

    public Enemy(int x, int y) {
        this.texture = new Texture(TEXTURE_NAME);
        this.sprite = new Sprite(texture);
        this.sprite.setSize(50, 50);
        this.sprite.setPosition(x, y);
        this.enemyRectangle = new Rectangle();
        this.bullets = new ArrayList<>();
    }

    public void FireBullet() {

        int bulletX = (int) (sprite.getX()  + sprite.getWidth() / 2 - Bullet.width / 2);
        int bulletY = (int) (sprite.getY() - sprite.getHeight() + Bullet.height);
        bullets.add(new Bullet(BULLET_NAME, bulletX, bulletY));
    }

    public List<Bullet> getBullets(){
        for (Bullet bullet : bullets){
            bullet.bounds.set(bullet.sprite.getX(),bullet.sprite.getY(), bullet.sprite.getWidth(), bullet.sprite.getHeight());
        }
        return bullets;
    }


    public void render(SpriteBatch batch){
        SpawnTimer += Gdx.graphics.getDeltaTime();

        if (SpawnTimer >= FIRE_RATE) {
            FireBullet();
            SpawnTimer = 0f;
        }
        batch.draw(texture, sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        for (int i = bullets.size() -1 ; i >= 0; i--) {
            bullets.get(i).move(Gdx.graphics.getDeltaTime(), bullet_speed);

            if (bullets.get(i).sprite.getY()  < 0) {
                bullets.remove(i);
            }
            else {
                bullets.get(i).render(batch);
            }
        }
    }


    public void dispose(){
        texture.dispose();
    }
}
