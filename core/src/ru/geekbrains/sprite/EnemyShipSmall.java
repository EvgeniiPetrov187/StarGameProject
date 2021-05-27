package ru.geekbrains.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;

public class EnemyShipSmall extends Sprite {

    private static final float HEIGHT = 0.15f;
    private static final float PADDING = -0.01f;
    private static final float RELOAD_INTERVAL = 0.45f;

    private static float speedX;

    private Rect worldBounds;
    private BulletPool bulletPool;
    private TextureRegion bulletRegion;
    private Vector2 bulletV;

    private final Vector2 v;

    private final Sound shot = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));

    private float reloadTimer;

    public EnemyShipSmall(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("enemy0"), 1, 2, 2);
        this.bulletPool = bulletPool;
        this.bulletRegion = atlas.findRegion("bulletEnemy");
        bulletV = new Vector2(0, -0.5f);
        speedX = (float) Math.random() / 10;
        v = new Vector2(speedX, -0.1f);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(HEIGHT);
        setTop(worldBounds.getTop() + PADDING);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        reloadTimer += delta;
        if (reloadTimer > RELOAD_INTERVAL) {
            reloadTimer = 0f;
            shoot();
        }
        if (getRight() > worldBounds.getRight()) {
            v.x = -speedX;
        }
        if (getLeft() < worldBounds.getLeft()) {
            v.x = speedX;
        }
        if (isOutside(worldBounds)) {
            setTop(worldBounds.getTop() + PADDING);
        }
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, this.pos, bulletV, worldBounds, 1, 0.01f);
        shot.play();
    }
}





