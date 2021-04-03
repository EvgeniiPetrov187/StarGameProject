package ru.geekbrains.sprite;

import com.badlogic.gdx.Game;

import ru.geekbrains.screen.MenuScreen;

public class StarGame extends Game {

	@Override
	public void create() {
		setScreen(new MenuScreen());
	}
}
