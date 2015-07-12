package luyentm.uet.desktop;

import luyentm.uet.main.MainGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 700;
//		config.fullscreen = true;
//		config.vSyncEnabled = true;
		new LwjglApplication(new MainGame(), config);
	}
}
