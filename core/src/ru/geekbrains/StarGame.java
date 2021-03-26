package ru.geekbrains;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StarGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	private Texture	backGround;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		backGround = new Texture("quad.jpg");
		img = new Texture("badlogic.jpg");

	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(backGround, 0,0);
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
