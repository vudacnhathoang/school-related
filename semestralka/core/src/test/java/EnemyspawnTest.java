import com.badlogic.gdx.Application;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Texture;
import cvut.fel.Enemy;
import cvut.fel.Enemyspawn;
import cvut.fel.TimeManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import java.util.List;
import static org.mockito.Mockito.*;

class EnemyspawnTest {

    private static MockedConstruction<Texture> textureMock;


    @BeforeAll
    static void initGraphicsMock() {
        // mockovani aplikace a ignorovani logovani

        Application app = mock(Application.class);
        doNothing().when(app).log(anyString(), anyString());
        Gdx.app = app;


        // mockovani grafiky
        Graphics gfx = mock(Graphics.class);
        when(gfx.getHeight()).thenReturn(480);
        when(gfx.getDeltaTime()).thenReturn(0f);
        Gdx.graphics = gfx;

        // mockovani souboru
        Files files = mock(Files.class);
        FileHandle dummy = mock(FileHandle.class);
        when(dummy.name()).thenReturn("enemy_easy.png");
        when(files.internal(anyString())).thenReturn(dummy);
        Gdx.files = files;

        textureMock = mockConstruction(Texture.class, (mock, ctx) -> {

            when(mock.getWidth()).thenReturn(50);
            when(mock.getHeight()).thenReturn(50);
        });
    }

    @AfterAll
    // zavreni mocku  texury
    static void tearDown() {
        textureMock.close();
    }



    @Test
    void spawnEnemieseasy(){
        TimeManager tm = new TimeManager();
        Enemyspawn spawner = new Enemyspawn(3, 1f, "enemy_easy.png", 5, tm);
        spawner.spawnEnemies(4);
        List<Enemy> list = spawner.getEnemies();
        assertEquals(1, list.size(), "Should have spawned exactly 3 enemies");

    }

    @Test
    void spawnEnemies_incrementsCountAndMarksGrid() {
        TimeManager tm = new TimeManager();
        Enemyspawn spawner = new Enemyspawn(3, 1f, "enemy_easy.png", 5, tm);

        spawner.spawnEnemies(3);
        spawner.spawnEnemies(3);
        spawner.spawnEnemies(3);

        List<Enemy> list = spawner.getEnemies();
        assertEquals(3, list.size(), "Should have spawned exactly 3 enemies");
        assertEquals(3, spawner.enemyCount, "enemyCount must match list size");

        // grid must have exactly 3 true cells
        long trueCells = 0;
        for (boolean[] row : spawner.grid) {
            for (boolean cell : row) {
                if (cell) trueCells++;
            }
        }
        assertEquals(3, trueCells, "Grid should be marked for each spawned enemy");
    }

    @Test
    void ExceedLimitSpawnEnemies(){
        TimeManager tm = new TimeManager();
        Enemyspawn spawner = new Enemyspawn(3, 1f, "enemy_easy.png", 5, tm);

        spawner.spawnEnemies(2);
        spawner.spawnEnemies(2);
        spawner.spawnEnemies(2);
        spawner.spawnEnemies(2);
        spawner.spawnEnemies(2);

        List<Enemy> list = spawner.getEnemies();
        assertEquals(2, list.size(), "Should have spawned exactly 2 enemies");
    }


    @Test
    void gridcheckafterexceeding() {
        TimeManager tm = new TimeManager();
        Enemyspawn spawner = new Enemyspawn(3, 1f, "enemy_easy.png", 5, tm);

        spawner.spawnEnemies(3);
        spawner.spawnEnemies(3);
        spawner.spawnEnemies(3);
        spawner.spawnEnemies(3);

        // grid must have exactly 3 true cells
        long trueCells = 0;
        for (boolean[] row : spawner.grid) {
            for (boolean cell : row) {
                if (cell) trueCells++;
            }
        }
        assertEquals(3, trueCells, "Grid should be marked for each spawned enemy");
    }
}
