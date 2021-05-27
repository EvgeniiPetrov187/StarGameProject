package ru.geekbrains.pool;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.base.SpritesPool;
import ru.geekbrains.sprite.EnemyShipSmall;

public class EnemyPool extends SpritesPool<EnemyShipSmall> {

    @Override
    protected EnemyShipSmall newSprite() {
        return new EnemyShipSmall(new TextureAtlas("textures/mainAtlas.tpack"), new BulletPool());
    }
}