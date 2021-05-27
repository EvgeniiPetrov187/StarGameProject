package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;

public class Star extends Sprite {

    private Vector2 v;
    private Rect worldBounds;

    private float height;

    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
        v = new Vector2(Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.1f, -0.05f));

    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        height = Rnd.nextFloat(0.008f, 0.015f);
        setHeightProportion(height);
        float x = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
        float y = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
        pos.set(x, y);
    }

    @Override
    public void update(float delta) {
        if (getHeight() >= height) {
            setHeightProportion(0.008f);
        } else {
            setHeightProportion(getHeight() + 0.0001f);
        }
        pos.mulAdd(v, delta);
        if (getRight() < worldBounds.getLeft()) {
            setLeft(worldBounds.getRight());
        }
        if (getLeft() > worldBounds.getRight()) {
            setRight(worldBounds.getLeft());
        }
        if (getTop() < worldBounds.getBottom()) {
            setBottom(worldBounds.getTop());
        }
        if (getBottom() > worldBounds.getTop()) {
            setTop(worldBounds.getBottom());
        }
    }
}
