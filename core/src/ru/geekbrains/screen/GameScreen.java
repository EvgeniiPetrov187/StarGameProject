package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;
import ru.geekbrains.sprite.Background;
import ru.geekbrains.sprite.EnemyShipSmall;
import ru.geekbrains.sprite.PlayerShip;
import ru.geekbrains.sprite.Star;

public class GameScreen extends BaseScreen {

    private static final int STAR_COUNT = 64;

    private Texture bg;
    private Background background;

    private TextureAtlas atlas;

    private Star stars[];
    private PlayerShip mainShip;

    private BulletPool bulletPool;

    private EnemyShipSmall enemy;
    //private EnemyPool enemyPool;

    private static final float NEW_ATTACK = 5f;
    private float newAttackTimer;

    private Music music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(bg);

        atlas = new TextureAtlas("textures/mainAtlas.tpack");

        stars = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            stars[i] = new Star(atlas);
        }

        bulletPool = new BulletPool();

        mainShip = new PlayerShip(atlas, bulletPool);

        enemy = new EnemyShipSmall(atlas, bulletPool);

        //enemyPool = new EnemyPool();

        music.play();
    }


    @Override
    public void render(float delta) {
        update(delta);
        freeAllDestroyed();
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        enemy.resize(worldBounds);
        //enemyPool.obtain().resize(worldBounds);
        mainShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        //enemyPool.dispose();
        music.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        mainShip.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        mainShip.touchUp(touch, pointer, button);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        newAttackTimer += delta;
        if (newAttackTimer > NEW_ATTACK) {
            newAttackTimer = 0f;
            System.out.println("Time to attack");
            //departure();
        }
        mainShip.update(delta);
        enemy.update(delta);
        //enemyPool.updateActiveSprites(delta);
        bulletPool.updateActiveSprites(delta);


    }

    private void freeAllDestroyed() {
        bulletPool.freeAllDestroyedActiveSprites();
        //enemyPool.freeAllDestroyedActiveSprites();
    }

    private void draw() {
        Gdx.gl.glClearColor(0.56f, 0.81f, 0.26f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        mainShip.draw(batch);
        enemy.draw(batch);
        //enemyPool.drawActiveSprites(batch);
        bulletPool.drawActiveSprites(batch);
        batch.end();
    }

    //public void departure() {
    //    EnemyShipSmall shipSmall = enemyPool.obtain();
    //}
}
