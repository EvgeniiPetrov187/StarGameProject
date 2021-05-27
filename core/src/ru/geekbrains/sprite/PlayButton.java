package ru.geekbrains.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.base.BaseButton;
import ru.geekbrains.math.Rect;
import ru.geekbrains.screen.GameScreen;

public class PlayButton extends BaseButton {

    private static final float HEIGHT = 0.25f;
    private static final float PADDING = 0.04f;

    private final Game game;

    public PlayButton(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("btPlay"));
        this.game = game;
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getBottom() + PADDING);
        setLeft(worldBounds.getLeft() + PADDING);
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen());
    }
}
