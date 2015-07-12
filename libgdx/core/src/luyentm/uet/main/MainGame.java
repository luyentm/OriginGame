package luyentm.uet.main;

import luyentm.uet.screen.MenuGameScreen;
import luyentm.uet.utils.Asserts;

import com.badlogic.gdx.Game;

public class MainGame extends Game {
	@Override
	public void create() {
		this.setScreen(new MenuGameScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		Asserts.source.dispose();
	}
}
