package ru.geekbrains.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;

public class Ship extends Sprite {

    protected Rect worldBounds;
    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletV;
    protected float bulletHeight;
    protected int damage;

    protected Vector2 v;
    protected Vector2 v0;

    protected float reloadTimer;
    protected float reloadInterval;
    protected boolean startShoot;

    protected Sound sound;

    protected int hp;

    public Ship() {
        bulletV = new Vector2();
        v = new Vector2();
        v0 = new Vector2(v.x,-0.4f); // быстрый выход врага на поле
        startShoot = true;
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
        bulletV = new Vector2();
        v = new Vector2();
        v0 = new Vector2();
    }

    @Override
    public void update(float delta) {
        reloadTimer += delta;
        if (this.getTop() < worldBounds.getTop()){
            pos.mulAdd(v, delta);
            if(startShoot) {
                reloadTimer = 0f;
                shoot();
                startShoot = false;   // первый выстрел при выходе врага на поле
            }
        } else {
            pos.mulAdd(v0, delta);
            startShoot = true;
        }

        if (reloadTimer > reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, this.pos, bulletV, worldBounds, damage, bulletHeight);
        sound.play();
    }
}
