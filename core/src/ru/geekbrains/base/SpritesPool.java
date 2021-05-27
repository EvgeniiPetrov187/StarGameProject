package ru.geekbrains.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public abstract class SpritesPool<T extends Sprite> {

    private final List<T> activeObjects = new ArrayList<>();
    private final List<T> freeObjects = new ArrayList<>();

    protected abstract T newSprite();

    public T obtain() {
        T sprite;
        if (freeObjects.isEmpty()) {
            sprite = newSprite();
        } else {
            sprite = freeObjects.remove(freeObjects.size() - 1);
        }
        activeObjects.add(sprite);
        System.out.println(getClass().getName() +" active/free : " + activeObjects.size() + "/" + freeObjects.size());
        return sprite;
    }

    public void updateActiveSprites(float delta) {
        for (Sprite sprite : activeObjects) {
            if (!sprite.isDestroyed()) {
                sprite.update(delta);
            }
        }
    }

    public void updateActiveSprites(float delta, float speed, int level) {
        for (Sprite sprite : activeObjects) {
            if (!sprite.isDestroyed()) {
                sprite.update(delta, speed, level);
            }
        }
    }

    public void drawActiveSprites(SpriteBatch spriteBatch) {
        for (Sprite sprite : activeObjects) {
            if (!sprite.isDestroyed()) {
                sprite.draw(spriteBatch);
            }
        }
    }

    public void freeAllDestroyedActiveSprites() {
        for (int i = 0; i < activeObjects.size(); i++) {
            T sprite = activeObjects.get(i);
            if (sprite.isDestroyed()) {
                free(sprite);
                i--;
                sprite.flushDestroy();
            }
        }
    }

    public void freeAllActiveObjects() {
        freeObjects.addAll(activeObjects);
        activeObjects.clear();
    }

    public void dispose() {
        activeObjects.clear();
        freeObjects.clear();
    }

    public List<T> getActiveObjects() {
        return activeObjects;
    }

    private void free(T sprite) {
        if (activeObjects.remove(sprite)) {
            freeObjects.add(sprite);
            System.out.println(getClass().getName() +" active/free : " + activeObjects.size() + "/" + freeObjects.size());
        }
    }

}