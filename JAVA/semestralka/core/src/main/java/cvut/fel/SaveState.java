package cvut.fel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonWriter;

import java.util.List;

public class SaveState {
    public int score;
    public ShipData ship;
    public SpawnerData enemyspawn;
    public  waveData wave;
    public int wavenumber;


    public SaveState(){
        this.wavenumber = 0;
        this.score = 0;
        this.ship = null;
        this.enemyspawn = null;
        this.wave = null;
    }

    public static class waveData{
        public int wave;
        public int killed;

        public waveData(){
        }

        public waveData(int wave, int killed){
            this.wave = wave;
            this.killed = killed;
        }
    }

    public static class BulletData{
        public float x;
        public float y;
        public String TextureName;

        BulletData(){
            this.x = 0;
            this.y = 0;
            this.TextureName = "";
        }
    }

    public static class EnemyData{
        public float x;
        public float y;
        public int row;
        public int col;
        public List<BulletData> bullets;

        EnemyData(){
            this.x = 0;
            this.y = 0;
            this.row = 0;
            this.col = 0;
            this.bullets = null;
        }
    }

    public static class SpawnerData{
        public float spawnrate;
        public int enemyCount;
        public int enemyLimit;
        public boolean[][] grid;
        public List<EnemyData> enemies;

        SpawnerData(){
            this.enemyCount = 0;
            this.enemyLimit = 0;
            this.grid = null;
            this.spawnrate = 0;
            this.enemies = null;
        }

    }

    public static class ShipData{
        public float x;
        public float y;
        public int health;
        public List<BulletData> bullets;

        public ShipData(){
            this.x = 0;
            this.y = 0;
            this.health = 0;
            this.bullets = null;
        }
    }

    public void saveGame(){
        FileHandle file = Gdx.files.local("saves/save.json");
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        String raw = json.toJson(this);
        String pretty = json.prettyPrint(raw);
        file.writeString(pretty, false);
    }

}
