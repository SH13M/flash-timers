package com.sh13m.flashtimers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class FlashTimers extends Game {
	public static int V_VIDTH = 640;
	public static int V_HEIGHT = 480;

	public SpriteBatch batch;
	public OrthographicCamera cam;
	public Viewport viewport;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_VIDTH, V_HEIGHT);
		viewport = new FitViewport(V_VIDTH, V_HEIGHT);
		setScreen(new UI(this));
	}

	@Override
	public void render () {
		super.render();
	}


	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
