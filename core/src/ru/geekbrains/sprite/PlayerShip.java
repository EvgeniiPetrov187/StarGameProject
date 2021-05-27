package ru.geekbrains.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class PlayerShip extends Sprite {


    private static final float V_LEN = 0.012f;
    private static final float Y_BOTTOM = -0.3f;
    private static final float X_RIGHT_BORDER = 0.26f;
    private static final float X_LEFT_BORDER = -0.26f;
    private Vector2 v;
    private Vector2 point;
    private Vector2 temp;

    public PlayerShip(TextureAtlas atlas) {
        super(new TextureRegion(atlas.findRegion("main_ship"), 0, 0, 195, 287));
        point = new Vector2();
        v = new Vector2();
        temp = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.2f);
        this.pos.set(worldBounds.pos.x, Y_BOTTOM);
    }

    @Override
    public void update(float delta) {
        temp.set(point);
        if (temp.sub(pos.x, Y_BOTTOM).len() < v.len()) {
            pos.set(point.x, Y_BOTTOM);
        } else {
            pos.add(v);
        }
        if (pos.x > X_RIGHT_BORDER) {
            pos.set(X_RIGHT_BORDER, Y_BOTTOM);
        }
        if (pos.x < X_LEFT_BORDER) {
            pos.set(X_LEFT_BORDER, Y_BOTTOM);
        }
    }

    public boolean TouchDown(Vector2 touch, int pointer, int button) {
        point.set(touch.x, Y_BOTTOM);
        v.set(touch.x, 0);
        v.setLength(V_LEN);
        return false;
    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.RIGHT:
                point.set(pos.x + 0.1f, Y_BOTTOM);
                v.set(0.1f, 0);
                v.setLength(V_LEN);
                break;
            case Input.Keys.LEFT:
                point.set(pos.x - 0.1f, Y_BOTTOM);
                v.set(-0.1f, 0);
                v.setLength(V_LEN);
                break;
        }
        return false;
    }
}
