package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class Logo extends Sprite {
    private static final float V_LEN = 0.003f;
    private Vector2 position;
    private Vector2 v;
    private Vector2 point;
    private Vector2 temp;


    public Logo(Texture texture) {
        super(new TextureRegion(texture));
        point = new Vector2();
        v = new Vector2();
        position = new Vector2(getLeft(), getBottom());
        temp = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.3f);
        this.pos.set(worldBounds.pos);
    }

    @Override
    public void draw(SpriteBatch batch) {
        temp.set(point);
        if (temp.sub(position).len() < v.len()) {
            position.set(point);
        } else {
            position.add(v);
        }
        batch.draw(
                new TextureRegion(new Texture("badlogic.jpg")),
                position.x, position.y,
                halfWidth, halfHeight,
                getWidth(), getHeight(),
                scale, scale,
                angle
        );
    }

    public void move(Vector2 touch, int pointer, int button) {
        point.set(touch);
        v.set(touch.cpy().sub(position).setLength(V_LEN));
    }
}
