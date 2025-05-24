package cvut.fel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.TimeUtils;
import cvut.fel.SaveState.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SpaceShip {
    private transient Texture texture;
    private transient Texture healthTexture;
    public transient Sprite sprite;
    private final static float  ship_speed = 200;
    int width = Gdx.graphics.getWidth();
    private final static int WIDTH_SHIP = 50;
    private final static int HEIGHT_SHIP= 50;
    private  final String TEXTURE_NAME= "tiny_ship17.png";
    private  final String BULLET_NAME= "laser_green.png";
    private  final String HEALTH_NAME= "health.png";
    private List<Bullet> bullets;
    private int bullet_speed = 300;
    private float lastFireTime = 0;
    private static final float FIRE_RATE = 0.5f;
    public int health = 3;
    private int x;
    private int y;


    public SpaceShip(){
        this.texture = new Texture(TEXTURE_NAME);
        this.health = health;
        this.healthTexture = new Texture(HEALTH_NAME);
        this.sprite = new Sprite(texture);
        this.sprite.setSize(WIDTH_SHIP, HEIGHT_SHIP);
        sprite.setPosition( (width- 50)/2, 20);
        this.bullets = new ArrayList<>();
    }

    public ShipData toSave(){
        ShipData shipData = new ShipData();
        shipData.x = sprite.getX();
        shipData.y = sprite.getY();
        shipData.health = health;
        shipData.bullets = new ArrayList<>();
        for (Bullet bullet : bullets) {
           shipData.bullets.add(bullet.toSave(BULLET_NAME));
        }
        return shipData;
    }

    public static SpaceShip toLoad(ShipData data){
        SpaceShip ship = new SpaceShip();
        ship.sprite.setPosition(data.x, data.y);
        ship.health = data.health;
        for (BulletData bulletData : data.bullets) {
            Bullet bullet = Bullet.toLoad(bulletData);
            ship.bullets.add(bullet);
        }
        return ship;
    }

    public void FireBullet() {

            int bulletX = (int) (sprite.getX() + sprite.getWidth() / 2 - Bullet.width / 2);
            int bulletY = (int) (sprite.getY() + sprite.getHeight());
            bullets.add(new Bullet(BULLET_NAME, bulletX, bulletY));
        }


    /**
     * Input handler for the spaceship.
     * @param delta
     */
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

        float currentTime = TimeUtils.nanoTime() / 1_000_000_000f;

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)&& currentTime - lastFireTime > FIRE_RATE )
        {
            FireBullet();
             lastFireTime = currentTime;
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

    /**
     * Updates the position of the bullets and removes them if they go off-screen.
     * @param delta Time delta since last frame.
     */
    public void updateBullets(float delta){
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            bullet.move(delta, bullet_speed);
            if (bullet.sprite.getY() > Gdx.graphics.getHeight()) {
                iterator.remove();
            }
        }
    }

    public void render(SpriteBatch batch) {
        //move
        HandleInput(Gdx.graphics.getDeltaTime());
        batch.draw(texture, sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());

        for (int i =0; i < health; i++){
            batch.draw(healthTexture, Gdx.graphics.getWidth() - 20 - i*20, Gdx.graphics.getHeight() - 30, 20, 20);
        }

        //move
        updateBullets(Gdx.graphics.getDeltaTime());
        for (int i = bullets.size() - 1; i >= 0; i--) {
                bullets.get(i).render(batch);
        }
    }

    public List<Bullet> getBullets() {
        return bullets;
    }
    public SpaceShip getSpaceShip() {
        return this;
    }

    public void dispose(){

       sprite.getTexture().dispose();
        if (healthTexture != null) {
            healthTexture.dispose();
        }
        for (Bullet bullet : bullets) {
            bullet.dispose();
        }
        bullets.clear();
    }


}
