package ru.geekbrains.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;
import ru.geekbrains.pool.ExplosionPool;

public class EnemyShip extends Ship {

    private final float SMALL_SHIP_SPEED_X = 0.02f;
    private final float OTHER_SHIP_SPEED_X = 0.01f;
    private final float SHIP_NAVI = 0.06f;


    public EnemyShip(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, Sound sound) {
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.worldBounds = worldBounds;
        this.sound = sound;
    }

    // с помощью нового метода update вражеские корабли могут преследовать игрока и с каждым уровнем двигаютя быстрее
    @Override
    public void update(float delta, float speed, int level) {
        super.update(delta, speed, level);
        if (getTop() < worldBounds.getTop()) {
            v.set(v0);
        } else {
            reloadTimer = reloadInterval * 0.8f;
        }
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
        }
        if (v.y > -0.1) {
            if (pos.x < speed)
                v.x = OTHER_SHIP_SPEED_X + (0.005f * level);
            if (pos.x > speed)
                v.x = -OTHER_SHIP_SPEED_X - (0.005f * level);
            if (getLeft() + SHIP_NAVI < speed && getRight() - SHIP_NAVI > speed) {
                v.x = 0;
            }
        } else {
            if (pos.x < speed)
                v.x = SMALL_SHIP_SPEED_X + (0.005f * level);
            if (pos.x > speed)
                v.x = -SMALL_SHIP_SPEED_X - (0.005f * level);
            if (getLeft() < speed && getRight() > speed) {
                v.x = 0;
            }
        }
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v,
            TextureRegion bulletRegion,
            float bulletHeight,
            float bulletVY,
            int damage,
            float reloadInterval,
            float height,
            int hp
    ) {
        this.regions = regions;
        this.v0.set(v);
        this.v.set(0, -0.3f);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0, bulletVY);
        this.damage = damage;
        this.reloadInterval = reloadInterval;
        setHeightProportion(height);
        this.hp = hp;
    }

    public boolean isBulletCollision(Rect bullet) {
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > getTop()
                || bullet.getTop() < pos.y);
    }
}
