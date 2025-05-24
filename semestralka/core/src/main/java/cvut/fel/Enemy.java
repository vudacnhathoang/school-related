package cvut.fel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import cvut.fel.SaveState.*;
import java.util.ArrayList;
import java.util.List;



/**
 * The Enemy class represents an enemy in the game.
 * It contains properties such as texture, sprite, bullets, and methods to fire bullets and render the enemy.
 */
public class Enemy {
    public String textureName;
    public transient Sprite sprite;
    private transient Texture texture;
    private List<Bullet> bullets;
    private int bullet_speed = -300;
    private float SpawnTimer = 0f;
    private static final float FIRE_RATE = 2f;
    public  final String BULLET_NAME= "laser_red.png";
    public Integer row;
    public Integer col;


    public Enemy(int x, int y, int row, int col, String textureName, int bullet_speed) {
        this.textureName = textureName;
        this.texture = new Texture(textureName);
        this.sprite = new Sprite(texture);
        this.sprite.setSize(50, 50);
        this.sprite.setPosition(x, y);
        this.bullets = new ArrayList<>();
        this.row = row;
        this.col = col;
        this.bullet_speed = bullet_speed;

        if (texture != null) {
            this.sprite = new Sprite(texture);
            this.sprite.setPosition(x, y);
        }
    }


    /**
     * Converts the enemy to a saveable format.
     * @return An EnemyData object containing the enemy's position, row, column, and bullets.
     */
    public EnemyData toSave(){
        EnemyData enemyData = new EnemyData();
        enemyData.x = sprite.getX();
        enemyData.y = sprite.getY();
        enemyData.row = row;
        enemyData.col = col;
        enemyData.bullets = new ArrayList<>();
        for (Bullet bullet : bullets) {
            enemyData.bullets.add(bullet.toSave(BULLET_NAME));
        }
        return enemyData;
    }

    /**
     * Creates an Enemy object from saved data.
     * @param data The saved enemy data.
     * @param textureName The name of the texture used for the enemy.
     * @param bullet_speed The speed of the bullets fired by the enemy.
     * @return An Enemy object initialized with the data.
     */
    public static Enemy toLoad(EnemyData data, String textureName, int bullet_speed) {
        Enemy enemy = new Enemy((int)data.x, (int)data.y, data.row, data.col, textureName, bullet_speed);
        for (BulletData bulletData : data.bullets) {
            Bullet bullet = Bullet.toLoad(bulletData);
            enemy.bullets.add(bullet);
        }
        return enemy;
    }

    /**
     * Fires a bullet from the enemy's position.
     * The bullet is created at the center of the enemy sprite and moves upwards.
     */
    public void FireBullet() {

        int bulletX = (int) (sprite.getX()  + sprite.getWidth() / 2 - Bullet.width / 2);
        int bulletY = (int) (sprite.getY() - sprite.getHeight() + Bullet.height);
        bullets.add(new Bullet(BULLET_NAME, bulletX, bulletY));
    }

    public List<Bullet> getBullets(){
        return bullets;
    }


    public void render(SpriteBatch batch){
        SpawnTimer += Gdx.graphics.getDeltaTime();

        if (SpawnTimer >= FIRE_RATE) {
            FireBullet();
            SpawnTimer = 0f;
        }
        batch.draw(texture, sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
       //movement of bullets
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


    public int getRowIndex() { return row; }
    public int getColIndex() { return col; }
    public float getBulletSpeed() { return bullet_speed; }

    public void dispose(){
        texture.dispose();
        sprite.getTexture().dispose();
        for (Bullet bullet : bullets) {
            bullet.dispose();
        }
    }
}
