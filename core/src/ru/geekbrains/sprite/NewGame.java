package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;


import ru.geekbrains.base.BaseButton;
import ru.geekbrains.math.Rect;
import ru.geekbrains.screen.GameScreen;

public class NewGame extends BaseButton {

    private final GameScreen gameScreen;

    public NewGame(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.gameScreen = gameScreen;
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.05f);
    }

    @Override
    public void action() {
        gameScreen.startNewGame();
    }
}

